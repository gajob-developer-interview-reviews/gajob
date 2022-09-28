package go.nkrcd.web.oauth2.model;

import go.nkrcd.web.user.model.User;
import lombok.Getter;

/*
 * OAuth2 로그인을 통해 가져온 사용자의 정보를 담음
 */
@Getter
public class UserProfile {
    private final String oauthId;
    private final String name;
    private final String email;
    private final String picture;

    public UserProfile(String oauthId, String name, String email, String picture) {
        this.oauthId = oauthId;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public User toUser() {
        return new User(oauthId, name, email, picture);
    }
}