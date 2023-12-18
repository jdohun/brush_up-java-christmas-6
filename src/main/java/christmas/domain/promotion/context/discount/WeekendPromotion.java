package christmas.domain.promotion.context.discount;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.precondition.DecemberPromotionPrecondition;
import christmas.domain.promotion.strategy.dateCheckStrategy.DayOfWeekCondition;
import christmas.domain.promotion.strategy.discountStrategy.byMenu.DiscountByDecemberEventPlan;
import christmas.dto.DiscountAmount;

import java.util.Optional;

public class WeekendPromotion implements DecemberPromotionPrecondition {
    public static final int WEEKEND_DISCOUNT_AMOUNT = 2_023;
    private static final DayOfWeekCondition DAY_OF_WEEK_CONDITION = DayOfWeekCondition.IS_WEEKEND;
    private static final DiscountByDecemberEventPlan DISCOUNT_BY_DECEMBER_EVENT_PLAN = DiscountByDecemberEventPlan.BY_MAIN_COUNT;

    private WeekendPromotion() {
    }

    public static WeekendPromotion getInstance() {
        return Holder.WEEKEND_PROMOTION;
    }

    public Optional<DiscountAmount> apply(DecemberEventPlan decemberEventPlan) {
        if (isApplicable(decemberEventPlan)) {
            return Optional.of(calculateDiscountAmount(decemberEventPlan));
        }
        return Optional.empty();
    }

    private boolean isApplicable(DecemberEventPlan decemberEventPlan) {
        return DecemberPromotionPrecondition.isSatisfyingPrecondition(decemberEventPlan)
                && DAY_OF_WEEK_CONDITION.isPlanSatisfyingCondition(decemberEventPlan);
    }

    private static DiscountAmount calculateDiscountAmount(DecemberEventPlan decemberEventPlan) {
        return DISCOUNT_BY_DECEMBER_EVENT_PLAN.calculateDiscountAmount(decemberEventPlan);
    }

    private static class Holder {
        private static final WeekendPromotion WEEKEND_PROMOTION = new WeekendPromotion();
    }
}
