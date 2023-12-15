package christmas.domain.model.classes.expectedVisitDay;

import christmas.dto.ExpectedVisitDayDto;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static christmas.domain.model.classes.expectedVisitDay.ExpectedVisitDayErrorMessage.ERROR_INVALID_EXPECTED_DAY;

public class ExpectedVisitDay {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int START_OF_RANGE = 1;
    private static final int END_OF_RANGE = 31;

    private static final Pattern EXPECTED_VISIT_DAY_PATTERN = Pattern.compile("^\\d+$");

    private final LocalDate date;

    private ExpectedVisitDay(LocalDate date) {
        this.date = date;
    }

    public static ExpectedVisitDay from(String inputExpectedVisitDay) {
        int expectedVisitDay = parseExpectedVisitDay(inputExpectedVisitDay);
        validate(expectedVisitDay);
        return new ExpectedVisitDay(LocalDate.of(YEAR, MONTH, expectedVisitDay));
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
        Matcher matcher = EXPECTED_VISIT_DAY_PATTERN.matcher(inputExpectedVisitDay);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ERROR_INVALID_EXPECTED_DAY.getMessage());
        }
        return Integer.parseInt(inputExpectedVisitDay);
    }

    public boolean isDateWithinEventPeriod(EventDateChecker eventDateChecker) {
        return eventDateChecker.isWithinEventPeriod(date);
    }

    public int calculateEventBenefitByDate(DateBasedDiscountCalculator dateBasedDiscountCalculator) {
        return dateBasedDiscountCalculator.calculateDiscount(date);
    }

    public ExpectedVisitDayDto toDto() {
        return new ExpectedVisitDayDto(date);
    }
}
