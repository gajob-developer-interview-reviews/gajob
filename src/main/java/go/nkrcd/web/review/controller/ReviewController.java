package go.nkrcd.web.review.controller;

import go.nkrcd.web.main.model.Company;
import go.nkrcd.web.main.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    CompanyRepository companyRepository;

    /*
     * 마이페이지 > 후기 작성하기
     */
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String review(Model model) {

        List<Company> companys = companyRepository.findAll();

        model.addAttribute("companys", companys);
        return "review";
    }
}
