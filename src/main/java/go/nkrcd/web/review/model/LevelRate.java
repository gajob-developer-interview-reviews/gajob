package go.nkrcd.web.review.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class LevelRate {
    @Enumerated(EnumType.STRING)
    private Level level;

    private Long count;

    @Setter
    private Long rate;

    public LevelRate(Level level, Long count) {
        this.level = level;
        this.count = count;
    }

}
