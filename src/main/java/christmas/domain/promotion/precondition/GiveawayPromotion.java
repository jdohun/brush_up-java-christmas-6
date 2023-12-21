package christmas.domain.promotion.precondition;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.dto.GiveawayAndQuantityDto;

import java.util.Optional;

public interface GiveawayPromotion extends ChristmasPromotionPrecondition {
    Optional<GiveawayAndQuantityDto> apply(DecemberEventPlan decemberEventPlan);
}
