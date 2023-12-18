package christmas.domain.promotion.context.discount;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.precondition.DecemberPromotionPrecondition;
import christmas.domain.promotion.strategy.dateCheckStrategy.SpecificDateCondition;
import christmas.dto.DiscountAmount;

import java.util.Optional;

public class SpecialDayPromotion implements DecemberPromotionPrecondition {
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1_000;
    private static final SpecificDateCondition SPECIFIC_DATE_CONDITION = SpecificDateCondition.SPECIAL_DATES;

    private SpecialDayPromotion() {
    }

    public static SpecialDayPromotion getInstance() {
        return Holder.SPECIAL_DAY_PROMOTION;
    }

    public Optional<DiscountAmount> apply(DecemberEventPlan decemberEventPlan) {
        if (isApplicable(decemberEventPlan)) {
            return Optional.of(new DiscountAmount(SPECIAL_DISCOUNT_AMOUNT));
        }
        return Optional.empty();
    }

    private boolean isApplicable(DecemberEventPlan decemberEventPlan) {
        return DecemberPromotionPrecondition.isSatisfyingPrecondition(decemberEventPlan)
                && SpecificDateCondition.SPECIAL_DATES.isSpecialDay(decemberEventPlan);
    }

    private static class Holder {
        private static final SpecialDayPromotion SPECIAL_DAY_PROMOTION = new SpecialDayPromotion();
    }
}
