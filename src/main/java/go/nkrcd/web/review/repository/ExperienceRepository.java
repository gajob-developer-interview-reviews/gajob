package go.nkrcd.web.review.repository;

import go.nkrcd.web.review.model.Experience;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    @Query("select new map(e.code.cdName as cd, count(1) as cnt) from Experience e where e.review.rid in :rId group by e.code.cdName order by cnt desc")
    List<Map<String, Object>> findTop3(@Param("rId") List<Long> rId, Pageable pageable);
}