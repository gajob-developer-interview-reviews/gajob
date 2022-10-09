package go.nkrcd.web.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import go.nkrcd.web.main.model.Code;
import go.nkrcd.web.main.model.Company;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eId;

    @ManyToOne
    @JoinColumn(name = "rId")
    @JsonIgnore
    private Review review;

    @ManyToOne
    @JoinColumn(name = "cdId", nullable = false)
    @JsonIgnore
    private Code code;

    @Column(nullable = false)
    private String content;

    @Builder
    Experience(Review review, Code code, String content) {
        this.review = review;
        this.code = code;
        this.content = content;
    }

    public Experience() { }
}
