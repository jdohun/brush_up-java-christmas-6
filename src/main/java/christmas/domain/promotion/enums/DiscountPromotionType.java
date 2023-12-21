package christmas.domain.promotion.enums;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.context.discount.DiscountPromotion;
import christmas.domain.promotion.context.discount.impl.ChristmasDDayPromotion;
import christmas.domain.promotion.context.discount.impl.SpecialDayPromotion;
import christmas.domain.promotion.context.discount.impl.WeekdayPromotion;
import christmas.domain.promotion.context.discount.impl.WeekendPromotion;
import christmas.dto.DiscountInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<DiscountInfo> applyPromotionTypes(DecemberEventPlan decemberEventPlan) {
        List<DiscountInfo> discountInfoList = new ArrayList<>();

        for (DiscountPromotionType discountPromotionType : DISCOUNT_PROMOTION_TYPES) {
            Optional<DiscountInfo> optionalDiscountInfo = discountPromotionType.promotion.apply(decemberEventPlan);

            optionalDiscountInfo.ifPresent(discountInfoList::add);
        }

        return discountInfoList;
    }
}
