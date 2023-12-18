package christmas.domain.promotion.context.giveaway;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.enums.Giveaway;
import christmas.domain.promotion.precondition.ChristmasPromotionPrecondition;
import christmas.domain.promotion.strategy.totalAmountCheckStrategy.TotalAmountCondition;
import christmas.dto.GiveawayAndQuantityDto;

import java.util.Optional;

public class GiveawayPromotion implements ChristmasPromotionPrecondition {
    private static final TotalAmountCondition TOTAL_AMOUNT_CONDITION = TotalAmountCondition.GIVEAWAY_EVENT_THRESHOLD;
    private static final String PROMOTION_NAME = "증정 이벤트";

    private GiveawayPromotion() {
    }

    public static GiveawayPromotion getInstance() {
        return Holder.GIVEAWAY_PROMOTION;
    }

    public Optional<GiveawayAndQuantityDto> apply(DecemberEventPlan decemberEventPlan) {
        if (isApplicable(decemberEventPlan)) {
            return Optional.of(createGiveaway());
        }
        return Optional.empty();
    }

    private boolean isApplicable(DecemberEventPlan decemberEventPlan) {
        return ChristmasPromotionPrecondition.isSatisfyingPrecondition(decemberEventPlan)
                && TOTAL_AMOUNT_CONDITION.isTotalAmountSufficient(decemberEventPlan.getTotalAmountBeforeDiscount());
    }

    private GiveawayAndQuantityDto createGiveaway() {
        return new GiveawayAndQuantityDto(Giveaway.GIVEAWAY_CHAMPAGNE, 1);
    }

    private static class Holder{
        private static final GiveawayPromotion GIVEAWAY_PROMOTION = new GiveawayPromotion();
    }
}
