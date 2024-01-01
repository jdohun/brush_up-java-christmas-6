package christmas.domain.promotion.strategy.dateCheckStrategy.impl;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.strategy.dateCheckStrategy.DateCheckStrategy;
import christmas.domain.promotion.strategy.dateCheckStrategy.DecemberEventPlanConditionChecker;

import java.time.LocalDate;

import static christmas.domain.promotion.context.ChristmasPromotionPrecondition.EVENT_MONTH;
import static christmas.domain.promotion.context.ChristmasPromotionPrecondition.EVENT_YEAR;

public enum PeriodCondition implements DecemberEventPlanConditionChecker, DateCheckStrategy {
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

    // DateCheckStrategy
    private boolean isDateWithinPeriod(LocalDate dateOfPlan) {
        return !dateOfPlan.isBefore(periodStarts) && !dateOfPlan.isAfter(periodEnds);
    }

    @Override
    public boolean isPlanSatisfyingCondition(DecemberEventPlan decemberEventPlan) {
        DateCheckStrategy dateCheckStrategy = this::isDateWithinPeriod;
        return decemberEventPlan.isDateSatisfyingDateCondition(dateCheckStrategy);
    }

    @Override
    public boolean isSatisfyingCondition(LocalDate date) {
        return isDateWithinPeriod(date);
    }
}
