package christmas.domain.promotion.context.discount.impl;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.context.discount.DiscountPromotion;
import christmas.domain.promotion.enums.PromotionName;
import christmas.domain.promotion.precondition.ChristmasPromotionPrecondition;
import christmas.domain.promotion.strategy.dateCheckStrategy.impl.SpecificDateCondition;
import christmas.dto.DiscountInfo;

import java.util.Optional;

public class SpecialDayPromotion implements DiscountPromotion {
    private static final PromotionName PROMOTION_NAME = PromotionName.SPECIAL_DAY_DISCOUNT;
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1_000;
    private static final SpecificDateCondition SPECIFIC_DATE_CONDITION = SpecificDateCondition.SPECIAL_DATES;

    private SpecialDayPromotion() {
    }

    public static SpecialDayPromotion getInstance() {
        return Holder.SPECIAL_DAY_PROMOTION;
    }

    @Override
    public Optional<DiscountInfo> apply(DecemberEventPlan decemberEventPlan) {
        if (isApplicable(decemberEventPlan)) {
            return Optional.of(new DiscountInfo(PROMOTION_NAME, SPECIAL_DISCOUNT_AMOUNT));
        }
        return Optional.empty();
    }

    private boolean isApplicable(DecemberEventPlan decemberEventPlan) {
        return ChristmasPromotionPrecondition.isSatisfyingPrecondition(decemberEventPlan)
                && SPECIFIC_DATE_CONDITION.isPlanSatisfyingCondition(decemberEventPlan);
    }

    private static class Holder {
        private static final SpecialDayPromotion SPECIAL_DAY_PROMOTION = new SpecialDayPromotion();
    }
}
