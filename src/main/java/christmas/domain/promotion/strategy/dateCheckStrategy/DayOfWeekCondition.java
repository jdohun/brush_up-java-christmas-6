package christmas.domain.promotion.strategy.dateCheckStrategy;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;

import java.time.DayOfWeek;

public enum DayOfWeekCondition {
    IS_WEEKDAY(dateOfPlan -> dateOfPlan.getDayOfWeek() != DayOfWeek.FRIDAY && dateOfPlan.getDayOfWeek() != DayOfWeek.SATURDAY),
    IS_WEEKEND(dateOfPlan -> dateOfPlan.getDayOfWeek() == DayOfWeek.FRIDAY || dateOfPlan.getDayOfWeek() == DayOfWeek.SATURDAY);

    private final DateCheckStrategy dateCheckStrategy;

    DayOfWeekCondition(DateCheckStrategy dateCheckStrategy) {
        this.dateCheckStrategy = dateCheckStrategy;
    }

    public boolean isPlanSatisfyingCondition(DecemberEventPlan decemberEventPlan) {
        return decemberEventPlan.isDateSatisfyingDateCondition(dateCheckStrategy);
    }
}
