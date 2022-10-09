package go.nkrcd.web.review.model;

public enum Level {
    low("LOW"),
    mid("MID"),
    high("HIGH");

    String level;

    Level(String level) {
        this.level = level;
    }

    public String value() {
        return level;
    }
}
