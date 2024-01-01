package christmas.domain.promotion.strategy.dateCheckStrategy.impl;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.strategy.dateCheckStrategy.DateCheckStrategy;
import christmas.domain.promotion.strategy.dateCheckStrategy.DecemberEventPlanConditionChecker;

import java.time.LocalDate;
import java.util.Set;

import static christmas.domain.promotion.context.ChristmasPromotionPrecondition.EVENT_MONTH;
import static christmas.domain.promotion.context.ChristmasPromotionPrecondition.EVENT_YEAR;

public enum SpecificDateCondition implements DecemberEventPlanConditionChecker, DateCheckStrategy {
    SPECIAL_DATES(Set.of(
            LocalDate.of(EVENT_YEAR, EVENT_MONTH, 3),
            LocalDate.of(EVENT_YEAR, EVENT_MONTH, 10),
            LocalDate.of(EVENT_YEAR, EVENT_MONTH, 17),
            LocalDate.of(EVENT_YEAR, EVENT_MONTH, 24),
            LocalDate.of(EVENT_YEAR, EVENT_MONTH, 25),
            LocalDate.of(EVENT_YEAR, EVENT_MONTH, 31)
    ));

    private final Set<LocalDate> specificDates;

    SpecificDateCondition(Set<LocalDate> specificDates) {
        this.specificDates = specificDates;
    }

    // DateCheckStrategy
    private boolean hasMatchingDate(LocalDate dateOfPlan) {
        return specificDates.contains(dateOfPlan);
    }

    @Override
    public boolean isPlanSatisfyingCondition(DecemberEventPlan decemberEventPlan) {
        return decemberEventPlan.isDateSatisfyingDateCondition(this::hasMatchingDate);
    }

    @Override
    public boolean isSatisfyingCondition(LocalDate date) {
        return hasMatchingDate(date);
    }
}

