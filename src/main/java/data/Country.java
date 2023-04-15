package data;

/**
 * Enum with countries and codes for it
 */
public enum Country {
    USA(1),
    SPAIN(2),
    CHINA(3),
    ITALY(4),
    JAPAN(5);
    private final int code;

    Country(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
