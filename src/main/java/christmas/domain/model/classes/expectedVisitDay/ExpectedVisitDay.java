package christmas.domain.model.classes.expectedVisitDay;

import christmas.domain.promotion.strategy.dateCheckStrategy.DateCheckStrategy;
import christmas.domain.promotion.strategy.discountStrategy.byDate.DateBasedDiscountStrategy;
import christmas.dto.ExpectedVisitDayDto;

import java.time.LocalDate;

import static christmas.domain.model.classes.expectedVisitDay.ExpectedVisitDayErrorMessage.ERROR_INVALID_EXPECTED_DAY;

public class ExpectedVisitDay {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int START_OF_RANGE = 1;
    private static final int END_OF_RANGE = 31;

    private final LocalDate date;

    private ExpectedVisitDay(LocalDate date) {
        this.date = date;
    }

    public static ExpectedVisitDay from(int dayOfMonth) {
        validate(dayOfMonth);
        return new ExpectedVisitDay(LocalDate.of(YEAR, MONTH, dayOfMonth));
    }

    private static void validate(int expectedVisitDay) {
        validateDayInRange(expectedVisitDay);
    }

    private static void validateDayInRange(int expectedVisitDay) {
        if (expectedVisitDay < START_OF_RANGE || expectedVisitDay > END_OF_RANGE) {
            throw new IllegalArgumentException(ERROR_INVALID_EXPECTED_DAY.getMessage());
        }
    }

    public boolean isSatisfyingCondition(DateCheckStrategy dateCheckStrategy) {
        return dateCheckStrategy.isSatisfyingCondition(date);
    }

    public int calculateDiscountAmountByDate(DateBasedDiscountStrategy dateBasedDiscountStrategy) {
        return dateBasedDiscountStrategy.calculateDiscountAmount(date);
    }

    public ExpectedVisitDayDto toDto() {
        return new ExpectedVisitDayDto(date);
    }
}
