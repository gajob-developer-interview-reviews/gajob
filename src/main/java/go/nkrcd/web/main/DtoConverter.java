package go.nkrcd.web.main;

import go.nkrcd.web.main.model.Company;
import go.nkrcd.web.main.model.CompanyDto;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {
    public CompanyDto toCompanyDto(Company company) {
        return new CompanyDto(company.getCId(), company.getName(), company.getAddress());
    }
}
