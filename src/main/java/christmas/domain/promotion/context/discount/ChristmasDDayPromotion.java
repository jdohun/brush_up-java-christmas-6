package christmas.domain.promotion.context.discount;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.enums.PromotionName;
import christmas.domain.promotion.precondition.ChristmasPromotionPrecondition;
import christmas.domain.promotion.precondition.DiscountPromotion;
import christmas.domain.promotion.strategy.dateCheckStrategy.impl.PeriodCondition;
import christmas.domain.promotion.strategy.discountStrategy.DiscountStrategy;
import christmas.domain.promotion.strategy.discountStrategy.byDate.DiscountByDate;
import christmas.dto.DiscountInfo;

import java.util.Optional;

public class ChristmasDDayPromotion implements DiscountPromotion {
    private static final PromotionName PROMOTION_NAME = PromotionName.CHRISTMAS_D_DAY_DISCOUNT;
    private static final PeriodCondition PERIOD_CONDITION = PeriodCondition.UNTIL_CHRISTMAS;
    private static final DiscountStrategy DISCOUNT_STRATEGY_BY_DATE = DiscountByDate.CHRISTMAS_D_DAY;

    private ChristmasDDayPromotion() {
    }

    public static ChristmasDDayPromotion getInstance() {
        return Holder.CHRISTMAS_D_DAY_PROMOTION;
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
                && PERIOD_CONDITION.isPlanSatisfyingCondition(decemberEventPlan);
    }

    private int calculateDiscountAmount(DecemberEventPlan decemberEventPlan) {
        return DISCOUNT_STRATEGY_BY_DATE.calculateDiscountAmount(decemberEventPlan);
    }

    private static class Holder {
        private static final ChristmasDDayPromotion CHRISTMAS_D_DAY_PROMOTION = new ChristmasDDayPromotion();
    }
}
