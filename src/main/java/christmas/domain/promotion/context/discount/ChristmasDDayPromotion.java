package christmas.domain.promotion.context.discount;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.dto.DiscountAmount;
import christmas.domain.promotion.strategy.dateCheckStrategy.PeriodCondition;
import christmas.domain.promotion.precondition.DecemberPromotionPrecondition;
import christmas.domain.promotion.strategy.discountStrategy.byDate.DiscountByDate;

import java.util.Optional;

public class ChristmasDDayPromotion implements DecemberPromotionPrecondition {
    public static final int DEFAULT_DISCOUNT_AMOUNT = 1_000;
    public static final int INCREASING_DISCOUNT_AMOUNT_PER_DAY = 100;
    private static final PeriodCondition PERIOD_CONDITION = PeriodCondition.UNTIL_CHRISTMAS;
    private static final DiscountByDate DISCOUNT_STRATEGY_BY_DATE = DiscountByDate.CHRISTMAS_D_DAY;

    public Optional<DiscountAmount> apply(DecemberEventPlan decemberEventPlan) {
        if (isApplicable(decemberEventPlan)) {
            return Optional.of(calculateDiscountAmount(decemberEventPlan));
        }
        return Optional.empty();
    }

    private boolean isApplicable(DecemberEventPlan decemberEventPlan) {
        return DecemberPromotionPrecondition.isSatisfyingPrecondition(decemberEventPlan)
                && PERIOD_CONDITION.isPlanWithinPeriod(decemberEventPlan);
    }

    private DiscountAmount calculateDiscountAmount(DecemberEventPlan decemberEventPlan) {
        return decemberEventPlan.calculateDiscountAmountByDate(DISCOUNT_STRATEGY_BY_DATE.getStrategy());
    }
}
