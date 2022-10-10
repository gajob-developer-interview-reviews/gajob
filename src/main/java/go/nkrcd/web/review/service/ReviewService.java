package go.nkrcd.web.review.service;

import go.nkrcd.web.main.model.Code;
import go.nkrcd.web.main.model.Company;
import go.nkrcd.web.main.model.User;
import go.nkrcd.web.main.repository.CodeRepository;
import go.nkrcd.web.main.repository.CompanyRepository;
import go.nkrcd.web.review.model.AddReview;
import go.nkrcd.web.review.model.Experience;
import go.nkrcd.web.review.model.Review;
import go.nkrcd.web.review.repository.ExperienceRepository;
import go.nkrcd.web.review.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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

}
