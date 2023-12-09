package christmas.domain.model.classes.expectedVisitDay;

import christmas.util.UtilPattern;

import java.time.LocalDate;
import java.util.regex.Matcher;

import static christmas.domain.model.classes.expectedVisitDay.ExpectedVisitDayErrorMessage.ERROR_INVALID_EXPECTED_DAY;

public class ExpectedVisitDay {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int START_OF_RANGE = 1;
    private static final int END_OF_RANGE = 31;
    private final LocalDate date;

    private ExpectedVisitDay(int expectedVisitDay) {
        validate(expectedVisitDay);
        this.date = LocalDate.of(YEAR, MONTH, expectedVisitDay);
    }

    public static ExpectedVisitDay from(String inputExpectedVisitDay) {
        int expectedVisitDay = parseExpectedVisitDay(inputExpectedVisitDay);
        return new ExpectedVisitDay(expectedVisitDay);
    }

    private static void validate(int expectedVisitDay) {
        validateDayInRange(expectedVisitDay);
    }

    private static void validateDayInRange(int expectedVisitDay) {
        if (expectedVisitDay < START_OF_RANGE || expectedVisitDay > END_OF_RANGE) {
            throw new IllegalArgumentException(ERROR_INVALID_EXPECTED_DAY.getMessage());
        }
    }

    private static int parseExpectedVisitDay(String inputExpectedVisitDay) {
        Matcher matcher = UtilPattern.INTEGER_PATTERN.getPattern().matcher(inputExpectedVisitDay);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ERROR_INVALID_EXPECTED_DAY.getMessage());
        }
        return Integer.parseInt(inputExpectedVisitDay);
    }
}
