package christmas.domain.promotion.strategy.discountStrategy.byDate;

import static christmas.domain.promotion.context.discount.ChristmasDDayPromotion.DEFAULT_DISCOUNT_AMOUNT;
import static christmas.domain.promotion.context.discount.ChristmasDDayPromotion.INCREASING_DISCOUNT_AMOUNT_PER_DAY;

public enum DiscountByDate {
    CHRISTMAS_D_DAY(dateOfPlan -> DEFAULT_DISCOUNT_AMOUNT + INCREASING_DISCOUNT_AMOUNT_PER_DAY * (dateOfPlan.getDayOfMonth() - 1));

    private final DateBasedDiscountStrategy strategy;

    DiscountByDate(DateBasedDiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public DateBasedDiscountStrategy getStrategy(){
        return strategy;
    }
}