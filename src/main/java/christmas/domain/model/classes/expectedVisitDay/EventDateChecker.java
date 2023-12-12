package christmas.domain.model.classes.expectedVisitDay;

import java.time.LocalDate;

@FunctionalInterface
public interface EventDateChecker {
    boolean isWithinEventPeriod(LocalDate date);
}
