package christmas.domain.promotion.strategy.discountStrategy.byDate;

import java.time.LocalDate;

@FunctionalInterface
public interface DateBasedDiscountStrategy {
    int calculateDiscount(LocalDate date);
}
