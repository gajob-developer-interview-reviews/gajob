package go.nkrcd.web.main.service;

import go.nkrcd.web.main.model.Company;
import go.nkrcd.web.main.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    CompanyRepository companyRepository;

    public List<Company> searchCompany(String search) {
        if(search.isEmpty())
            return null;
        return companyRepository.findCompanyList(search, PageRequest.of(0, 10));
    }
}
