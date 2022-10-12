package go.nkrcd.web.main.service;

import go.nkrcd.web.main.model.User;
import go.nkrcd.web.main.repository.UserRepository;
import go.nkrcd.web.oauth2.model.CustomOAuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUserByOauthId(String oauthId) {
        return userRepository.findUserByOauthId(oauthId);
    }

    public User join(Authentication authentication) {
        CustomOAuthUser oAuthUser = (CustomOAuthUser) authentication.getPrincipal();
        User user = userRepository.save(oAuthUser.getUser());
        return user;
    }
}
