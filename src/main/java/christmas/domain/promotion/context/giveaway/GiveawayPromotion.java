package christmas.domain.promotion.context.giveaway;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.context.ChristmasPromotionPrecondition;
import christmas.dto.GiveawayInfo;

import java.util.Optional;

public interface GiveawayPromotion extends ChristmasPromotionPrecondition {
    Optional<GiveawayInfo> apply(DecemberEventPlan decemberEventPlan);
}
