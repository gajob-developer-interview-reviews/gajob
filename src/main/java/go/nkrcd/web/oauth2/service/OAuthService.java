package go.nkrcd.web.oauth2.service;

import go.nkrcd.web.oauth2.model.CustomOAuthUser;
import go.nkrcd.web.oauth2.model.OAuthAttributes;
import go.nkrcd.web.oauth2.model.SessionUser;
import go.nkrcd.web.oauth2.model.UserProfile;
import go.nkrcd.web.main.model.User;
import go.nkrcd.web.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class OAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;

    @Autowired
    public OAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest); // OAuth 서비스(github, google, naver)에서 가져온 유저 정보를 담고있음

        // OAuth 서비스 이름(ex. github, naver, google)
        String registrationId = userRequest.getClientRegistration()
                .getRegistrationId();
        // OAuth 로그인 시 키(pk)가 되는 값
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        // OAuth 서비스의 유저 정보들
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // registrationId에 따라 유저 정보를 통해 공통된 UserProfile 객체로 만들어 줌
        UserProfile userProfile = OAuthAttributes.extract(registrationId, attributes);

        Optional<User> byOauthId = userRepository.findByOauthIdAndDelYn(userProfile.getOauthId(), "N");
        boolean join;
        User user;
        if(byOauthId.isEmpty()) {
            // 신규 회원
            user = userProfile.toUser();
            join = false;
        } else {
            // 기존 회원
            user = byOauthId.get();
            user.update(userProfile.getName(), userProfile.getEmail(), userProfile.getPicture());
            join = true;
        }

        return new CustomOAuthUser(attributes, userNameAttributeName, user, join);
    }

    // 현재 미사용
    private User saveOrUpdate(UserProfile userProfile) {
        // 이미 DB 에 저장된 유저 정보가 있는 경우에는 OAuth 서비스 사이트에서 유저 정보 변경이 있을 수 있기 때문에 우리 DB에도 update,
        // 없는 경우에는 새로 추가
        User user = (User) userRepository.findByOauthId(userProfile.getOauthId())
                .map(m -> m.update(userProfile.getName(), userProfile.getEmail(), userProfile.getPicture()))
                .orElse(userProfile.toUser());
        return userRepository.save(user);
    }
}