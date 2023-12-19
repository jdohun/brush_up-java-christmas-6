package christmas.domain.promotion.strategy.dateCheckStrategy.impl;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.strategy.dateCheckStrategy.DateCheckStrategy;

import java.time.DayOfWeek;

public enum DayOfWeekCondition implements DecemberEventPlanConditionChecker {
    IS_WEEKDAY(dateOfPlan -> dateOfPlan.getDayOfWeek() != DayOfWeek.FRIDAY && dateOfPlan.getDayOfWeek() != DayOfWeek.SATURDAY),
    IS_WEEKEND(dateOfPlan -> dateOfPlan.getDayOfWeek() == DayOfWeek.FRIDAY || dateOfPlan.getDayOfWeek() == DayOfWeek.SATURDAY);

    private final DateCheckStrategy dateCheckStrategy;

    DayOfWeekCondition(DateCheckStrategy dateCheckStrategy) {
        this.dateCheckStrategy = dateCheckStrategy;
    }

    @Override
    public boolean isPlanSatisfyingCondition(DecemberEventPlan decemberEventPlan) {
        return decemberEventPlan.isDateSatisfyingDateCondition(dateCheckStrategy);
    }
}
