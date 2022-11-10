package go.nkrcd.web.main.controller;

import go.nkrcd.web.main.model.Company;
import go.nkrcd.web.main.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final CompanyService companyService;

    @Autowired
    public SearchController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /*
     * 회사명 자동완성
     */
    @RequestMapping(value = "/company", method = {RequestMethod.POST})
    public List<Company> searchCompany(String search) {
        return companyService.searchCompany(search);
    }
}
