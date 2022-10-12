package go.nkrcd.web.oauth2.model;

import go.nkrcd.web.main.model.User;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.List;
import java.util.Map;

@Getter
public class CustomOAuthUser extends DefaultOAuth2User {
    private User user;
    private boolean join;   // 가입 여부

    public CustomOAuthUser(Map<String, Object> attributes, String nameAttributeKey, User user, boolean join) {
        super(List.of(new SimpleGrantedAuthority(user.getRole())), attributes, nameAttributeKey);
        this.user = user;
        this.join = join;
    }
}
