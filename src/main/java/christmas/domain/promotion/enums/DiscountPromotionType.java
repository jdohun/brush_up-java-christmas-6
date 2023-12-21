package christmas.domain.promotion.enums;

import christmas.domain.promotion.context.discount.DiscountPromotion;
import christmas.domain.promotion.context.discount.impl.ChristmasDDayPromotion;
import christmas.domain.promotion.context.discount.impl.SpecialDayPromotion;
import christmas.domain.promotion.context.discount.impl.WeekdayPromotion;
import christmas.domain.promotion.context.discount.impl.WeekendPromotion;

public enum DiscountPromotionType {
    CHRISTMAS_D_DAY(ChristmasDDayPromotion.getInstance()),
    WEEKDAY(WeekdayPromotion.getInstance()),
    WEEKEND(WeekendPromotion.getInstance()),
    SPECIAL_DAY(SpecialDayPromotion.getInstance());

    private static final DiscountPromotionType[] DISCOUNT_PROMOTION_TYPES = DiscountPromotionType.values();
    private final DiscountPromotion promotion;

    DiscountPromotionType(DiscountPromotion promotion) {
        this.promotion = promotion;
    }
}
