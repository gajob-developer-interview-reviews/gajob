package go.nkrcd.web.main.service;

import go.nkrcd.web.main.model.Company;
import go.nkrcd.web.main.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company findCompanyView(String cId) {
        return companyRepository.findCompanyView(cId);
    }

    public List<Company> CompanyList(String search) {
        return companyRepository.findCompanyListAll(search, PageRequest.of(0, 50));
    }

    public List<Company> searchCompany(String search) {
        if(search.isEmpty())
            return null;
        return companyRepository.findCompanyList(search, PageRequest.of(0, 10));
    }

    public Company findByCid(String cId) {
        return companyRepository.findByCid(cId);
    }
}
