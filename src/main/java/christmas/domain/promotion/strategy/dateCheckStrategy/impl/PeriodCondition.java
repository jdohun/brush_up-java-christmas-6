package christmas.domain.promotion.strategy.dateCheckStrategy.impl;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;

import java.time.LocalDate;

import static christmas.domain.promotion.precondition.ChristmasPromotionPrecondition.EVENT_MONTH;
import static christmas.domain.promotion.precondition.ChristmasPromotionPrecondition.EVENT_YEAR;

public enum PeriodCondition implements DecemberEventPlanConditionChecker {
     UNTIL_CHRISTMAS(
            LocalDate.of(EVENT_YEAR, EVENT_MONTH, 1),
            LocalDate.of(EVENT_YEAR, EVENT_MONTH, 25)
    ),
    MONTHLY_DECEMBER(
            LocalDate.of(EVENT_YEAR, EVENT_MONTH, 1),
            LocalDate.of(EVENT_YEAR, EVENT_MONTH, 31)
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

    private boolean isDateWithinPeriod(LocalDate dateOfPlan) {
        return !dateOfPlan.isBefore(periodStarts) && !dateOfPlan.isAfter(periodEnds);
    }

    @Override
    public boolean isPlanSatisfyingCondition(DecemberEventPlan decemberEventPlan) {
        return decemberEventPlan.isDateSatisfyingDateCondition(this::isDateWithinPeriod);
    }
}
