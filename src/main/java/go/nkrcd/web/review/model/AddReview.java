package go.nkrcd.web.review.model;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class AddReview {

    private String cId;

    @Enumerated(EnumType.STRING)
    private Level level;

    private String experience;
}

