package christmas.domain.promotion.precondition;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.dto.DiscountInfo;

import java.util.Optional;

public interface DiscountPromotion extends ChristmasPromotionPrecondition {
    Optional<DiscountInfo> apply(DecemberEventPlan decemberEventPlan);
}
