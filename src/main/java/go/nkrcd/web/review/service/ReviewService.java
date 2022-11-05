package go.nkrcd.web.review.service;

import go.nkrcd.web.main.model.User;
import go.nkrcd.web.review.model.AddReview;
import go.nkrcd.web.review.model.LevelRate;
import go.nkrcd.web.review.model.Review;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReviewService {

    @Transactional(isolation = Isolation.DEFAULT)
    public void save(AddReview addReview, User user);

    /*
        회사에 대한 후기
     */
    public List<Review> findReviews(String cId);

    /*
        회사의 채용 난이도 비율
        - 지원자들이 느낀 난이도에요
     */
    public List<LevelRate> findLevelRate(String cId);

    /*
        회사의 채용 방식 TOP 3
        - 가장 많이 채용한 방식 TOP3에요
     */
    public List<String> findExperienceTop3(String cId);

    /**
     * 사용자가 쓴 리뷰 목록
     * @param uId
     * @return
     */
    public List<Review> findReviewByUser(Long uId);

    /**
     * 후기 삭제
     * @param rid
     * @return
     */
    @Transactional
    public int del(String rid);
}
