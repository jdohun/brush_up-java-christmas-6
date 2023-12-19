package christmas.domain.promotion.strategy.discountStrategy;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;

@FunctionalInterface
public interface DiscountStrategy {
    int calculateDiscountAmount(DecemberEventPlan decemberEventPlan);
}
