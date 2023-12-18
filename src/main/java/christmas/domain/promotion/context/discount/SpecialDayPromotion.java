package christmas.domain.promotion.context.discount;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.dto.DiscountAmount;
import christmas.domain.promotion.strategy.dateCheckStrategy.SpecificDateCondition;
import christmas.domain.promotion.precondition.DecemberPromotionPrecondition;

import java.util.Optional;

public class SpecialDayPromotion implements DecemberPromotionPrecondition {
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1_000;
    private static final SpecificDateCondition SPECIFIC_DATE_CONDITION = SpecificDateCondition.SPECIAL_DATES;

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
}
