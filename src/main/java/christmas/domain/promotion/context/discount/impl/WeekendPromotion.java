package christmas.domain.promotion.context.discount.impl;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.context.discount.DiscountPromotion;
import christmas.domain.promotion.enums.PromotionName;
import christmas.domain.promotion.precondition.ChristmasPromotionPrecondition;
import christmas.domain.promotion.strategy.dateCheckStrategy.impl.DayOfWeekCondition;
import christmas.domain.promotion.strategy.discountStrategy.byDecemberEventPlan.DiscountByDecemberEventPlan;
import christmas.dto.DiscountInfo;

import java.util.Optional;

public class WeekendPromotion implements DiscountPromotion {
    private static final PromotionName PROMOTION_NAME = PromotionName.WEEKEND_DISCOUNT;
    public static final int WEEKEND_DISCOUNT_AMOUNT = 2_023;
    private static final DayOfWeekCondition DAY_OF_WEEK_CONDITION = DayOfWeekCondition.IS_WEEKEND;
    private static final DiscountByDecemberEventPlan DISCOUNT_BY_DECEMBER_EVENT_PLAN = DiscountByDecemberEventPlan.BY_MAIN_COUNT;

    private WeekendPromotion() {
    }

    public static WeekendPromotion getInstance() {
        return Holder.WEEKEND_PROMOTION;
    }

    @Override
    public Optional<DiscountInfo> apply(DecemberEventPlan decemberEventPlan) {
        if (isApplicable(decemberEventPlan)) {
            return Optional.of(new DiscountInfo(PROMOTION_NAME, calculateDiscountAmount(decemberEventPlan)));
        }
        return Optional.empty();
    }

    private boolean isApplicable(DecemberEventPlan decemberEventPlan) {
        return ChristmasPromotionPrecondition.isSatisfyingPrecondition(decemberEventPlan)
                && DAY_OF_WEEK_CONDITION.isPlanSatisfyingCondition(decemberEventPlan);
    }

    private int calculateDiscountAmount(DecemberEventPlan decemberEventPlan) {
        return DISCOUNT_BY_DECEMBER_EVENT_PLAN.calculateDiscountAmount(decemberEventPlan);
    }

    private static class Holder {
        private static final WeekendPromotion WEEKEND_PROMOTION = new WeekendPromotion();
    }
}
