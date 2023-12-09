package christmas.domain.model.classes.orderInfo;

import java.util.regex.Pattern;

import static christmas.domain.model.classes.orderInfo.OrderInfo.ORDER_DELIMITER;
import static christmas.domain.model.classes.orderInfo.OrderInfo.ORDER_MENU_DELIMITER;
import static christmas.util.UtilPattern.INTEGER_PATTERN;
import static christmas.util.UtilPattern.KOREAN_PATTERN;

public enum OrderInfoPattern {
    ORDER_MENU_PATTERN('^' + KOREAN_PATTERN.getRegex() + ORDER_MENU_DELIMITER + INTEGER_PATTERN.getRegex() + '$'),
    ORDER_FORMAT("^\\s*" + ORDER_MENU_PATTERN.getRegex() + "\\s*(" + ORDER_DELIMITER + "\\s*" + ORDER_MENU_PATTERN.getRegex() + ")*\\s*$");

    private final String regex;

    OrderInfoPattern(String regex) {
        this.regex = regex;
    }

    private String getRegex() {
        return regex;
    }

    public Pattern getPattern() {
        return Pattern.compile(regex);
    }
}
