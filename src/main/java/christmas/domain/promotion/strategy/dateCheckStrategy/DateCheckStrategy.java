package christmas.domain.promotion.strategy.dateCheckStrategy;

import java.time.LocalDate;

@FunctionalInterface
public interface DateCheckStrategy {
    boolean isSatisfyingCondition(LocalDate date);
}
