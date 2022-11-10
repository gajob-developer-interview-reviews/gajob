package go.nkrcd.web.oauth2.config;

import go.nkrcd.web.main.model.User;
import go.nkrcd.web.main.service.UserService;
import go.nkrcd.web.oauth2.model.CustomOAuthUser;
import go.nkrcd.web.oauth2.model.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomOAuthSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService;
    private final HttpSession httpSession;

    @Autowired
    public CustomOAuthSuccessHandler(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomOAuthUser oAuthUser = (CustomOAuthUser) authentication.getPrincipal();
        User user = oAuthUser.getUser();
        boolean join = oAuthUser.isJoin();

        httpSession.setAttribute("user", new SessionUser(user));

        if(join) {
//            userRepository.save(user);
            userService.save(user);
            response.sendRedirect("/");
        } else {
            // 회원가입 페이지로 이동
            response.sendRedirect("/join");
        }
    }
}
