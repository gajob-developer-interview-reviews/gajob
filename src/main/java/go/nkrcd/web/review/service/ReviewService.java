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
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CodeRepository codeRepository;

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    ReviewRepository reviewRepository;

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
            Experience experience = Experience.builder()
                    .review(review)
                    .code(code)
                    .content(content)
                    .build();
            experience = experienceRepository.save(experience);
        }
    }

}
