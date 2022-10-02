package go.nkrcd.web.oauth2.model;

import go.nkrcd.web.main.model.User;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/*
 * 인증된 사용자의 정보를 담음
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    @Builder
    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
