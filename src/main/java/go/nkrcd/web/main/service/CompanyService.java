package go.nkrcd.web.main.service;

import go.nkrcd.web.main.DtoConverter;
import go.nkrcd.web.main.model.Company;
import go.nkrcd.web.main.model.CompanyDto;
import go.nkrcd.web.main.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DtoConverter dtoConverter;

    public CompanyDto findCompanyView(String cId) {
//        return companyRepository.findCompanyView(cId);
        return dtoConverter.toCompanyDto(companyRepository.findCompanyView(cId));
    }
}
