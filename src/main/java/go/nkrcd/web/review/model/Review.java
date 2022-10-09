package go.nkrcd.web.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import go.nkrcd.web.main.model.Company;
import go.nkrcd.web.main.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Long rid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cId", nullable = false)
    @JsonIgnore
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uId")
    @JsonIgnore
    private User user;

    @CreatedDate
    private String writeDt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level level;

    private String delYn;
    private String delDt;

    @OneToMany(mappedBy = "review")
    private List<Experience> experience = new ArrayList<>();

    @Builder
    public Review(Company company, User user, Level level) {
        this.company = company;
        this.user = user;
        this.level = level;
    }

    public Review() {

    }
}
