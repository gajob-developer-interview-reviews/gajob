package go.nkrcd.web.review;

import go.nkrcd.web.review.repository.ExperienceRepository;
import go.nkrcd.web.review.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ReviewTest {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ExperienceRepository experienceRepository;

    /*
        후기 보기 > 가장 많이 채용된 방식 TOP3
     */
    @Test
    void findExperienceTop3(){
        List<String> top3 = new ArrayList<>();

        // 1. 리뷰 id 수집
        List<Long> rIds = reviewRepository.findRIds("CP001");
        for(Long rId: rIds) {
//			System.out.println(rId);
        }

        // 2. 리뷰 id 를 이용해 가장 많은 유형 3가지 수집
        List<Map<String, Object>> test = experienceRepository.findTop3(rIds, PageRequest.of(0, 3));
        for(Map<String, Object> t: test) {
//			System.out.println(t.get("cd"));
//			System.out.println(t.get("cnt"));

            // 3. top3에 이름만 저장
            top3.add((String) t.get("cd"));
        }

        for (String t: top3) {
            System.out.println(t);
        }
    }

}
