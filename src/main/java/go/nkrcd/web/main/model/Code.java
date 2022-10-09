package go.nkrcd.web.main.model;

import go.nkrcd.web.review.model.Experience;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor
public class Code {
    @Id
    private String cdId;

    private String cdName;
    private String useYn;

    @OneToMany(mappedBy = "code")
    private List<Experience> experience = new ArrayList<>();
}
