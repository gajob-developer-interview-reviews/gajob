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
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DtoConverter dtoConverter;

    public List<CompanyDto> searchCompany(String search) {
        if(search.isEmpty())
            return null;

        return companyRepository.findCompanyList(search, PageRequest.of(0, 10))
                .stream()
                .map(dtoConverter::toCompanyDto)
                .collect(Collectors.toList());
    }
}
