package go.nkrcd.web.review.service;

import go.nkrcd.web.main.model.Code;
import go.nkrcd.web.main.model.Company;
import go.nkrcd.web.main.model.User;
import go.nkrcd.web.main.service.CodeService;
import go.nkrcd.web.main.service.CompanyService;
import go.nkrcd.web.review.model.AddReview;
import go.nkrcd.web.review.model.Experience;
import go.nkrcd.web.review.model.LevelRate;
import go.nkrcd.web.review.model.Review;
import go.nkrcd.web.review.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    private final CompanyService companyService;
    private final CodeService codeService;
    private final ExperienceService experienceService;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(CompanyService companyService, CodeService codeService, ExperienceService experienceService, ReviewRepository reviewRepository) {
        this.companyService = companyService;
        this.codeService = codeService;
        this.experienceService = experienceService;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void save(AddReview addReview, User user) {
        Company company = companyService.findByCid(addReview.getCId());
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

            Code code = codeService.findByCId(cdId);
            if (code == null) {
                // 존재하지 않는 코드를 사용하는 경우 에러 발생 시킴
                throw new RuntimeException("존재하지 않는 코드값");
            }
            Experience experience = Experience.builder()
                    .review(review)
                    .code(code)
                    .content(content)
                    .build();
            experience = experienceService.save(experience);
        }
    }

    @Override
    public List<Review> findReviews(String cId) {
        return reviewRepository.findReviews(cId);
    }

    @Override
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

    @Override
    public List<String> findExperienceTop3(String cId) {
        List<String> top3 = new ArrayList<>();

        // 1. 리뷰 id 수집
        List<Long> rIds = reviewRepository.findRIds(cId);

        // 2. 리뷰 id 를 이용해 가장 많은 유형 3가지 수집
        List<Map<String, Object>> test = experienceService.findTop3(rIds);
        for(Map<String, Object> t: test) {
            // 3. top3에 이름만 저장
            top3.add((String) t.get("cd"));
        }

        return top3;
    }

    @Override
    public List<Review> findReviewByUser(Long uId) {
        return reviewRepository.findReviewByUser(uId);
    }

    @Override
    public int del(String rid) {
        try {
            int del = reviewRepository.del(Long.parseLong(rid), LocalDateTime.now());
            return del;
        } catch (Exception e) {
            log.error(e.getMessage());
            return -1;
        }
    }
}
