package christmas.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static christmas.domain.model.classes.expectedVisitDay.ExpectedVisitDayErrorMessage.ERROR_INVALID_EXPECTED_DAY;

public final class InputDayOfMonthParser {
    private static final Pattern EXPECTED_VISIT_DAY_PATTERN = Pattern.compile("^\\d{1,2}$");

    public static int parseDayOfMonth(String inputDayOfMonth) {
        Matcher matcher = EXPECTED_VISIT_DAY_PATTERN.matcher(inputDayOfMonth);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(ERROR_INVALID_EXPECTED_DAY.getMessage());
        }

        return Integer.parseInt(inputDayOfMonth);
    }
}
