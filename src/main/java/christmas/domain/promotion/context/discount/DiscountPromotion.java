package christmas.domain.promotion.context.discount;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.context.ChristmasPromotionPrecondition;
import christmas.dto.DiscountInfo;

import java.util.Optional;

public interface DiscountPromotion extends ChristmasPromotionPrecondition {
    Optional<DiscountInfo> apply(DecemberEventPlan decemberEventPlan);
}
