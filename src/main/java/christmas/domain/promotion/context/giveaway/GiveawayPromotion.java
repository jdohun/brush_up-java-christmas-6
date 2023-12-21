package christmas.domain.promotion.context.giveaway;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.precondition.ChristmasPromotionPrecondition;
import christmas.dto.GiveawayAndQuantityDto;

import java.util.Optional;

public interface GiveawayPromotion extends ChristmasPromotionPrecondition {
    Optional<GiveawayAndQuantityDto> apply(DecemberEventPlan decemberEventPlan);
}
