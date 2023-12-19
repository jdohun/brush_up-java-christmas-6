package christmas.domain.promotion.strategy.discountStrategy.byDecemberEventPlan;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.strategy.discountStrategy.DiscountStrategy;

import static christmas.domain.promotion.context.discount.WeekdayPromotion.WEEKDAY_DISCOUNT_AMOUNT;
import static christmas.domain.promotion.context.discount.WeekendPromotion.WEEKEND_DISCOUNT_AMOUNT;

public enum DiscountByDecemberEventPlan implements DiscountStrategy {
    BY_DESSERT_COUNT(decemberEventPlan -> WEEKDAY_DISCOUNT_AMOUNT * decemberEventPlan.countDessertMenus()),
    BY_MAIN_COUNT(decemberEventPlan -> WEEKEND_DISCOUNT_AMOUNT * decemberEventPlan.countMainMenus());

    private final DiscountStrategy strategy;

    DiscountByDecemberEventPlan(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public int calculateDiscountAmount(DecemberEventPlan decemberEventPlan) {
        return strategy.calculateDiscountAmount(decemberEventPlan);
    }
}
