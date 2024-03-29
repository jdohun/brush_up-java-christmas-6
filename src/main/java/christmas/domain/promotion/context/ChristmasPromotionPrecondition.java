package christmas.domain.promotion.context;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.strategy.dateCheckStrategy.impl.PeriodCondition;
import christmas.domain.promotion.strategy.totalAmountCheckStrategy.TotalAmountCondition;

public interface ChristmasPromotionPrecondition {
    int EVENT_YEAR = 2023;
    int EVENT_MONTH = 12;

    PeriodCondition PERIOD_CONDITION = PeriodCondition.MONTHLY_DECEMBER;
    TotalAmountCondition TOTAL_AMOUNT_CONDITION = TotalAmountCondition.MINIMUM_ORDER_AMOUNT;

    static boolean isSatisfyingPrecondition(DecemberEventPlan decemberEventPlan) {
        return PERIOD_CONDITION.isPlanSatisfyingCondition(decemberEventPlan)
                && TOTAL_AMOUNT_CONDITION.isTotalAmountSufficient(decemberEventPlan.getTotalAmountBeforeDiscount());
    }
}
