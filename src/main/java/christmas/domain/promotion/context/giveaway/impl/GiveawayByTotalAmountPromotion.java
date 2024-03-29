package christmas.domain.promotion.context.giveaway.impl;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.enums.Giveaway;
import christmas.domain.promotion.context.giveaway.GiveawayPromotion;
import christmas.domain.promotion.enums.PromotionName;
import christmas.domain.promotion.context.ChristmasPromotionPrecondition;
import christmas.domain.promotion.strategy.totalAmountCheckStrategy.TotalAmountCondition;
import christmas.dto.GiveawayInfo;

import java.util.Optional;

public class GiveawayByTotalAmountPromotion implements GiveawayPromotion {
    private static final PromotionName PROMOTION_NAME = PromotionName.GIVEAWAY_EVENT;
    private static final TotalAmountCondition TOTAL_AMOUNT_CONDITION = TotalAmountCondition.GIVEAWAY_EVENT_THRESHOLD;

    private GiveawayByTotalAmountPromotion() {
    }

    public static GiveawayByTotalAmountPromotion getInstance() {
        return Holder.GIVEAWAY_PROMOTION;
    }

    @Override
    public Optional<GiveawayInfo> apply(DecemberEventPlan decemberEventPlan) {
        if (isApplicable(decemberEventPlan)) {
            return Optional.of(createGiveaway());
        }
        return Optional.empty();
    }

    private boolean isApplicable(DecemberEventPlan decemberEventPlan) {
        return ChristmasPromotionPrecondition.isSatisfyingPrecondition(decemberEventPlan)
                && TOTAL_AMOUNT_CONDITION.isTotalAmountSufficient(decemberEventPlan.getTotalAmountBeforeDiscount());
    }

    private GiveawayInfo createGiveaway() {
        return new GiveawayInfo(PROMOTION_NAME, Giveaway.GIVEAWAY_CHAMPAGNE, 1);
    }

    private static class Holder{
        private static final GiveawayByTotalAmountPromotion GIVEAWAY_PROMOTION = new GiveawayByTotalAmountPromotion();
    }
}
