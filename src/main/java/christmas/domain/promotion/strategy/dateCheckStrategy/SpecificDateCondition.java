package christmas.domain.promotion.strategy.dateCheckStrategy;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;

import java.time.LocalDate;
import java.util.Set;

import static christmas.domain.promotion.precondition.ChristmasPromotionPrecondition.EVENT_PERIOD_MONTH;
import static christmas.domain.promotion.precondition.ChristmasPromotionPrecondition.EVENT_PERIOD_YEAR;

public enum SpecificDateCondition {
    SPECIAL_DATES(Set.of(
            LocalDate.of(EVENT_PERIOD_YEAR, EVENT_PERIOD_MONTH, 3),
            LocalDate.of(EVENT_PERIOD_YEAR, EVENT_PERIOD_MONTH, 10),
            LocalDate.of(EVENT_PERIOD_YEAR, EVENT_PERIOD_MONTH, 17),
            LocalDate.of(EVENT_PERIOD_YEAR, EVENT_PERIOD_MONTH, 24),
            LocalDate.of(EVENT_PERIOD_YEAR, EVENT_PERIOD_MONTH, 25),
            LocalDate.of(EVENT_PERIOD_YEAR, EVENT_PERIOD_MONTH, 31)
    ));

    private final Set<LocalDate> specificDates;

    SpecificDateCondition(Set<LocalDate> specificDates) {
        this.specificDates = specificDates;
    }

    public boolean isSpecialDay(DecemberEventPlan decemberEventPlan) {
        return decemberEventPlan.isDateSatisfyingDateCondition(this::hasMatchingDate);
    }

    private boolean hasMatchingDate(LocalDate dateOfPlan) {
        return specificDates.contains(dateOfPlan);
    }
}

