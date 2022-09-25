package go.nkrcd.web.crawler;

import go.nkrcd.web.company.model.Company;
import go.nkrcd.web.company.repository.CompanyRepository;
import go.nkrcd.web.crawler.Service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crawler")
public class CrawlerController {
    @Autowired
    CrawlerService crawlerService;

    @Autowired
    CompanyRepository companyRepository;

    @GetMapping("/list")
    public List<Company> list() {
        if(crawlerService.list()) {
            return companyRepository.findAll();
        }
        return null;
    }
}
