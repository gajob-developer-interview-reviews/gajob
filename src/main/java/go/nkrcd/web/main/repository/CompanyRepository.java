package go.nkrcd.web.main.repository;

import go.nkrcd.web.main.model.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value = "select CONCAT('CP',LPAD(REPLACE(max(c_id),'CP','')+1, '3', '0')) from company c", nativeQuery = true)
    String findCIdNext();

    @Query("select c from Company c where c.name like %:search% and c.delYn = 'N'")
    List<Company> findCompanyList(@Param("search") String search, Pageable pageable);
}
