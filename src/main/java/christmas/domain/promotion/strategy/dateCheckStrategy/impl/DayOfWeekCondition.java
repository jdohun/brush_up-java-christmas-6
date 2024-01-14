package christmas.domain.promotion.strategy.dateCheckStrategy.impl;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.strategy.dateCheckStrategy.DateCheckStrategy;
import christmas.domain.promotion.strategy.dateCheckStrategy.DecemberEventPlanConditionChecker;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum DayOfWeekCondition implements DecemberEventPlanConditionChecker, DateCheckStrategy {
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

    @Override
    public boolean isSatisfyingCondition(LocalDate date) {
        return this.dateCheckStrategy.isSatisfyingCondition(date);
    }
}
