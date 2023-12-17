package christmas.domain.promotion.strategy.discountStrategy.byMenu;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;

@FunctionalInterface
public interface DecemberEventPlanBasedDiscountStrategy {
    int calculateDiscountAmount(DecemberEventPlan decemberEventPlan);
}
