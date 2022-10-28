package go.nkrcd.web.review.repository;

import go.nkrcd.web.review.model.LevelRate;
import go.nkrcd.web.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.company.cId like %:cId% and r.delYn = 'N' order by r.writeDt desc ")
    List<Review> findReviews(@Param("cId") String cId);

    @Query("select count(1)" +
            "from Review r " +
            "where r.company.cId like %:cId% and r.delYn = 'N'")
    List<Long> findReviewCount(@Param("cId") String cId);

    @Query("select new go.nkrcd.web.review.model.LevelRate(r.level as level, COUNT(1) as count)" +
            "from Review r " +
            "where r.company.cId like %:cId% and r.delYn = 'N'" +
            "group by r.level")
    List<LevelRate> findLevelRate(@Param("cId") String cId);

    @Query("select r.rid from Review r where r.company.cId like %:cId% and r.delYn = 'N'")
    List<Long> findRIds(@Param("cId") String cId);

    @Query("select r from Review r where r.user.uid = :uid and r.delYn ='N' order by r.rid desc ")
    List<Review> findReviewByUser(@Param("uid") Long uId);
}
