package christmas.util;

import java.util.regex.Pattern;

public enum UtilPattern {
    INTEGER_PATTERN("^\\d+$"),
    KOREAN_PATTERN("^[가-힣]+$");

    private final String regex;

    UtilPattern(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    public Pattern getPattern() {
        return Pattern.compile(regex);
    }
}
