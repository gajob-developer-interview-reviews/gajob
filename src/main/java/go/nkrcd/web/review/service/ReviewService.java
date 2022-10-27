package go.nkrcd.web.review.service;

import go.nkrcd.web.main.model.Code;
import go.nkrcd.web.main.model.Company;
import go.nkrcd.web.main.model.User;
import go.nkrcd.web.main.repository.CodeRepository;
import go.nkrcd.web.main.repository.CompanyRepository;
import go.nkrcd.web.review.model.AddReview;
import go.nkrcd.web.review.model.Experience;
import go.nkrcd.web.review.model.LevelRate;
import go.nkrcd.web.review.model.Review;
import go.nkrcd.web.review.repository.ExperienceRepository;
import go.nkrcd.web.review.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ReviewService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CodeRepository codeRepository;

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Transactional(isolation = Isolation.DEFAULT)
    public void save(AddReview addReview, User user) {
        Company company = companyRepository.findByCid(addReview.getCId());
        Review review = Review.builder()
                .company(company)
                .user(user)
                .level(addReview.getLevel())
                .build();
        review = reviewRepository.save(review);

        JSONObject jsonObject = new JSONObject(addReview.getExperience());
        JSONArray jsonArray = jsonObject.getJSONArray("experience");
        for (int i=0; i<jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);

            String cdId = object.getString("cdId");
            String content = object.getString("content");

            Code code = codeRepository.findByCId(cdId);
            if (code == null) {
                // 존재하지 않는 코드를 사용하는 경우 에러 발생 시킴
                throw new RuntimeException("존재하지 않는 코드값");
            }
            Experience experience = Experience.builder()
                    .review(review)
                    .code(code)
                    .content(content)
                    .build();
            experience = experienceRepository.save(experience);
        }
    }

    /*
        회사에 대한 후기
     */
    public List<Review> findReviews(String cId) {
        return reviewRepository.findReviews(cId);
    }

    /*
        회사의 채용 난이도 비율
        - 지원자들이 느낀 난이도에요
     */
    public List<LevelRate> findLevelRate(String cId) {
        Long allCnt = reviewRepository.findReviewCount(cId).get(0);

        List<LevelRate> levelRates = reviewRepository.findLevelRate(cId);
        for (LevelRate lv : levelRates) {
            Long cnt =  lv.getCount();
            double rate = ( (double)cnt / (double)allCnt ) * 100.00;
            lv.setRate(Math.round(rate));
        }

        return levelRates;
    }

    /*
        회사의 채용 방식 TOP 3
        - 가장 많이 채용한 방식 TOP3에요
     */
    public List<String> findExperienceTop3(String cId) {
        List<String> top3 = new ArrayList<>();

        // 1. 리뷰 id 수집
        List<Long> rIds = reviewRepository.findRIds(cId);

        // 2. 리뷰 id 를 이용해 가장 많은 유형 3가지 수집
        List<Map<String, Object>> test = experienceRepository.findTop3(rIds, PageRequest.of(0, 3));
        for(Map<String, Object> t: test) {
            // 3. top3에 이름만 저장
            top3.add((String) t.get("cd"));
        }

        return top3;
    }
}
