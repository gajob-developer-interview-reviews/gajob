package go.nkrcd.web.mypage.contoroller;

import go.nkrcd.web.main.model.User;
import go.nkrcd.web.main.service.UserService;
import go.nkrcd.web.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MypageController {
    private final UserService userService;
    private final ReviewService reviewService;

    @Autowired
    public MypageController(UserService userService, ReviewService reviewService) {
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @RequestMapping(value = "/mypage", method = RequestMethod.GET)
    public String mypage(Model model, Authentication authentication) {
        // 로그인 유저 정보를 가져옴
//        System.out.println(authentication.getName());
        User profile = userService.findProfile(authentication.getName());

        model.addAttribute("profile", profile);
        model.addAttribute("reviews", reviewService.findReviewByUser(profile.getUid()));
        return "mypage";
    }
}
