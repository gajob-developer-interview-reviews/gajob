package go.nkrcd.web.review.model;

public enum Level {
    LOW("쉬워요"),
    MID("보통이에요"),
    HIGH("어려워요");

    private final String label;

    Level(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
