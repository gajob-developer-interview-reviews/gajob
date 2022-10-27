package go.nkrcd.web.main.service;

import go.nkrcd.web.main.model.Company;
import go.nkrcd.web.main.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    @Autowired
    CompanyRepository companyRepository;

    public List<Company> CompanyList(String search) {
        return companyRepository.findCompanyListAll(search, PageRequest.of(0, 50));
    }
}
