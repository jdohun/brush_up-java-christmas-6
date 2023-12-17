package christmas.domain.promotion.strategy.discountStrategy.byMenu;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.dto.DiscountAmount;

import static christmas.domain.promotion.context.discount.WeekdayPromotion.WEEKDAY_DISCOUNT_AMOUNT;
import static christmas.domain.promotion.context.discount.WeekendPromotion.WEEKEND_DISCOUNT_AMOUNT;

public enum DiscountByDecemberEventPlan {
    BY_DESSERT_COUNT(decemberEventPlan -> WEEKDAY_DISCOUNT_AMOUNT * decemberEventPlan.countDessertMenus()),
    BY_MAIN_COUNT(decemberEventPlan -> WEEKEND_DISCOUNT_AMOUNT * decemberEventPlan.countMainMenus())
    ;

    private final DecemberEventPlanBasedDiscountStrategy strategy;

    DiscountByDecemberEventPlan(DecemberEventPlanBasedDiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public DiscountAmount calculateDiscountAmount(DecemberEventPlan decemberEventPlan){
        return new DiscountAmount(strategy.calculateDiscountAmount(decemberEventPlan));
    }
}
