package go.nkrcd.web.company.repository;

import go.nkrcd.web.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value = "select CONCAT('CP',LPAD(REPLACE(max(c_id),'CP','')+1, '3', '0')) from company c", nativeQuery = true)
    String findCIdNext();
}
