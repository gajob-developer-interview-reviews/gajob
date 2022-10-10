package go.nkrcd.web.review.controller;

import go.nkrcd.web.main.model.Code;
import go.nkrcd.web.main.model.User;
import go.nkrcd.web.main.repository.CodeRepository;
import go.nkrcd.web.main.service.UserService;
import go.nkrcd.web.review.model.AddReview;
import go.nkrcd.web.review.model.Level;
import go.nkrcd.web.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    CodeRepository codeRepository;

    /*
     * 마이페이지 > 후기 작성하기
     */
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String review(Model model) {
        List<Code> codes = codeRepository.findCodeList();
        AddReview addReview = new AddReview();
        addReview.setLevel(Level.valueOf("mid"));

        model.addAttribute("addReview", addReview);
        model.addAttribute("codes", codes);

        return "review";
    }

}
