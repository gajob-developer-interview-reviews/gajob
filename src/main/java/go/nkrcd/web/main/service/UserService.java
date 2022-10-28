package go.nkrcd.web.main.service;

import go.nkrcd.web.main.model.User;
import go.nkrcd.web.main.repository.UserRepository;
import go.nkrcd.web.oauth2.model.CustomOAuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findByOauthIdAndDelYn(String oauthId) {
        Optional<User> user = userRepository.findByOauthIdAndDelYn(oauthId, "N");
        return user.orElse(new User());
    }

    public User join(Authentication authentication) {
        CustomOAuthUser oAuthUser = (CustomOAuthUser) authentication.getPrincipal();
        User user = userRepository.save(oAuthUser.getUser());
        return user;
    }

    @Transactional
    public int out(Authentication authentication) {
        CustomOAuthUser oAuthUser = (CustomOAuthUser) authentication.getPrincipal();
        return userRepository.joinOut(oAuthUser.getUser().getUid(), LocalDateTime.now());
    }
}
