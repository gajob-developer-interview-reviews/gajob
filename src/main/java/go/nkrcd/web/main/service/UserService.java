package go.nkrcd.web.main.service;

import go.nkrcd.web.main.model.User;
import go.nkrcd.web.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUserByOauthId(String oauthId) {
        return userRepository.findUserByOauthId(oauthId);
    }
}
