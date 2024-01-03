package christmas.domain.promotion.strategy.discountStrategy.byDate;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.strategy.discountStrategy.DiscountStrategy;

public enum DiscountByDate implements DiscountStrategy {
    CHRISTMAS_D_DAY(dateOfPlan -> {
        final int DEFAULT_DISCOUNT_AMOUNT = 1_000;
        final int INCREASING_DISCOUNT_AMOUNT_PER_DAY = 100;
        return DEFAULT_DISCOUNT_AMOUNT + INCREASING_DISCOUNT_AMOUNT_PER_DAY * (dateOfPlan.getDayOfMonth() - 1);
    });

    private final DateBasedDiscountStrategy strategy;

    DiscountByDate(DateBasedDiscountStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public int calculateDiscountAmount(DecemberEventPlan decemberEventPlan) {
        return decemberEventPlan.calculateDiscountAmountByDate(strategy);
    }
}
