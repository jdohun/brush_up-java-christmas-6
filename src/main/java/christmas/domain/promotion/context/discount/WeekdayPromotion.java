package christmas.domain.promotion.context.discount;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.precondition.ChristmasPromotionPrecondition;
import christmas.domain.promotion.strategy.dateCheckStrategy.DayOfWeekCondition;
import christmas.domain.promotion.strategy.discountStrategy.byMenu.DiscountByDecemberEventPlan;
import christmas.dto.DiscountAmount;

import java.util.Optional;

public class WeekdayPromotion implements ChristmasPromotionPrecondition {
    public static final int WEEKDAY_DISCOUNT_AMOUNT = 2_023;
    private static final DayOfWeekCondition DAY_OF_WEEK_CONDITION = DayOfWeekCondition.IS_WEEKDAY;
    private static final DiscountByDecemberEventPlan DISCOUNT_BY_DECEMBER_EVENT_PLAN = DiscountByDecemberEventPlan.BY_DESSERT_COUNT;
    private static final String PROMOTION_NAME = "평일 할인";

    private WeekdayPromotion() {
    }

    public static WeekdayPromotion getInstance() {
        return Holder.WEEKDAY_PROMOTION;
    }

    public Optional<DiscountAmount> apply(DecemberEventPlan decemberEventPlan) {
        if (isApplicable(decemberEventPlan)) {
            return Optional.of(calculateDiscountAmount(decemberEventPlan));
        }
        return Optional.empty();
    }

    private boolean isApplicable(DecemberEventPlan decemberEventPlan) {
        return ChristmasPromotionPrecondition.isSatisfyingPrecondition(decemberEventPlan)
                && DAY_OF_WEEK_CONDITION.isPlanSatisfyingCondition(decemberEventPlan);
    }

    private static DiscountAmount calculateDiscountAmount(DecemberEventPlan decemberEventPlan) {
        return DISCOUNT_BY_DECEMBER_EVENT_PLAN.calculateDiscountAmount(decemberEventPlan);
    }

    private static class Holder {
        private static final WeekdayPromotion WEEKDAY_PROMOTION = new WeekdayPromotion();
    }
}
