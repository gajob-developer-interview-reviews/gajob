package go.nkrcd.web.main.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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

    @Builder
    public Company(String cId, Company company) {
        this.cId = cId;
        this.name = company.getName();
        this.address = company.getAddress();
        this.logo = company.getLogo();
    }

    @Builder
    public Company(String name, String address, String logo) {
        Assert.hasText(name, "name is empty");

        this.name = name;
        this.address = address;
        this.logo = logo;
    }
}
