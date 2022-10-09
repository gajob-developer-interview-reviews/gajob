package go.nkrcd.web.main.model;

import go.nkrcd.web.review.model.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uId;

    @Column(nullable = false)
    private String oauthId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;
    private String picture;

    @Column(nullable = false)
    private String role = "ROLE_USER";

    @CreatedDate
    private String joinDt;

    private String delYn;
    private String delDt;

    @OneToMany(mappedBy = "user")
    private List<Review> review = new ArrayList<>();

    @Builder
    public User(String oauthId, String name, String email, String picture) {
        // 안전한 객체 생성 패턴을 위해 추가
        // DB 칼럼이 Not Null 인 경우 엔티티 값도 null 이면 안됨
        Assert.hasText(oauthId, "oauthId is empty");
        Assert.hasText(name, "name is empty");
        Assert.hasText(email, "email is empty");

        this.oauthId = oauthId;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public Object update(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;

        return this;
    }
}