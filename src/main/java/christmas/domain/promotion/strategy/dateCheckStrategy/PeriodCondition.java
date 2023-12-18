package christmas.domain.promotion.strategy.dateCheckStrategy;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;

import java.time.LocalDate;

import static christmas.domain.promotion.precondition.ChristmasPromotionPrecondition.EVENT_PERIOD_MONTH;
import static christmas.domain.promotion.precondition.ChristmasPromotionPrecondition.EVENT_PERIOD_YEAR;

public enum PeriodCondition {
     UNTIL_CHRISTMAS(
            LocalDate.of(EVENT_PERIOD_YEAR, EVENT_PERIOD_MONTH, 1),
            LocalDate.of(EVENT_PERIOD_YEAR, EVENT_PERIOD_MONTH, 25)
    ),
    MONTHLY_DECEMBER(
            LocalDate.of(EVENT_PERIOD_YEAR, EVENT_PERIOD_MONTH, 1),
            LocalDate.of(EVENT_PERIOD_YEAR, EVENT_PERIOD_MONTH, 31)
    );

    private final LocalDate periodStarts;
    private final LocalDate periodEnds;

    PeriodCondition(
            LocalDate periodStarts,
            LocalDate periodEnds
    ) {
        this.periodStarts = periodStarts;
        this.periodEnds = periodEnds;
    }

    public boolean isPlanWithinPeriod(DecemberEventPlan decemberEventPlan) {
        return decemberEventPlan.isDateSatisfyingDateCondition(this::isDateWithinPeriod);
    }

    private boolean isDateWithinPeriod(LocalDate dateOfPlan) {
        return !dateOfPlan.isBefore(periodStarts) && !dateOfPlan.isAfter(periodEnds);
    }
}
