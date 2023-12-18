package christmas.domain.promotion.context.discount;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.precondition.ChristmasPromotionPrecondition;
import christmas.domain.promotion.strategy.dateCheckStrategy.PeriodCondition;
import christmas.domain.promotion.strategy.discountStrategy.byDate.DiscountByDate;
import christmas.dto.DiscountAmount;

import java.util.Optional;

public class ChristmasDDayPromotion implements ChristmasPromotionPrecondition {
    public static final int DEFAULT_DISCOUNT_AMOUNT = 1_000;
    public static final int INCREASING_DISCOUNT_AMOUNT_PER_DAY = 100;
    private static final PeriodCondition PERIOD_CONDITION = PeriodCondition.UNTIL_CHRISTMAS;
    private static final DiscountByDate DISCOUNT_STRATEGY_BY_DATE = DiscountByDate.CHRISTMAS_D_DAY;

    private static final String PROMOTION_NAME = "크리스마스 디데이 할인";

    private ChristmasDDayPromotion() {
    }

    public static ChristmasDDayPromotion getInstance() {
        return Holder.CHRISTMAS_D_DAY_PROMOTION;
    }

    public Optional<DiscountAmount> apply(DecemberEventPlan decemberEventPlan) {
        if (isApplicable(decemberEventPlan)) {
            return Optional.of(calculateDiscountAmount(decemberEventPlan));
        }
        return Optional.empty();
    }

    private boolean isApplicable(DecemberEventPlan decemberEventPlan) {
        return ChristmasPromotionPrecondition.isSatisfyingPrecondition(decemberEventPlan)
                && PERIOD_CONDITION.isPlanWithinPeriod(decemberEventPlan);
    }

    private DiscountAmount calculateDiscountAmount(DecemberEventPlan decemberEventPlan) {
        return DISCOUNT_STRATEGY_BY_DATE.applyDiscount(decemberEventPlan);
    }

    private static class Holder {
        private static final ChristmasDDayPromotion CHRISTMAS_D_DAY_PROMOTION = new ChristmasDDayPromotion();
    }
}
