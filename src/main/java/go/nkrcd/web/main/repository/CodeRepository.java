package go.nkrcd.web.main.repository;

import go.nkrcd.web.main.model.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, Long> {

    @Query("select c from Code c where c.useYn = 'Y'")
    List<Code> findCodeList();

    @Query("select c from Code c where c.cdId = :cdId and c.useYn = 'Y'")
    Code findByCId(@Param("cdId")String cdId);
}
