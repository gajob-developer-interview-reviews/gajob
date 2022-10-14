package go.nkrcd.web.main.model;

import go.nkrcd.web.review.model.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor
public class Company {
    @Id
    private String cId;

    @NotEmpty
    private String name;

    private String address;
    private String logo;

    private String delYn;

    private String delDt;

    @OneToMany(mappedBy = "company")
    private List<Review> review = new ArrayList<>();

    /* 크롤링 수집한 데이터의 C_ID 값 붙여줄 때 사용 */
    @Builder
    public Company(String cId, Company company) {
        this.cId = cId;
        this.name = company.getName();
        this.address = company.getAddress();
        this.logo = company.getLogo();
    }

    /* 크롤링 데이터 수집시 사용 */
    public Company res(String name, String address, String logo) {
        Assert.hasText(name, "name is empty");

        this.name = name;
        this.address = address;
        this.logo = logo;

        return this;
    }
}
