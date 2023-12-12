package christmas.domain.model.classes.expectedVisitDay;

import java.time.LocalDate;

@FunctionalInterface
public interface DateBasedDiscountCalculator {
    int calculateDiscount(LocalDate date);
}
