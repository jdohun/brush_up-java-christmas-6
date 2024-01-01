package christmas.fixture;

import christmas.domain.model.classes.expectedVisitDay.ExpectedVisitDay;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ExpectedVisitDayFixture {

    WEEKDAY(new int[]{3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31}),
    WEEKEND(new int[]{1, 2, 8, 9, 15, 16, 22, 23, 29, 30}),
    SPECIAL_DAYS(new int[]{3, 10, 17, 24, 25, 31}),
    NOT_SPECIAL_DAYS(new int[]{1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 26, 27, 28, 29, 30}),
    UNTIL_CHRISTMAS(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25}),
    AFTER_CHRISTMAS(new int[]{26, 27, 28, 29, 30, 31}),
    MONTHLY_DECEMBER(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31});

    int[] daysOfMonth;

    ExpectedVisitDayFixture(int[] daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }

    public List<ExpectedVisitDay> getDays() {
        return Arrays.stream(daysOfMonth)
                .mapToObj(daysOfMonth -> ExpectedVisitDay.from(daysOfMonth))
                .collect(Collectors.toList());
    }
}
