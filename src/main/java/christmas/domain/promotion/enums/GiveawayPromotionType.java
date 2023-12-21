package christmas.domain.promotion.enums;

import christmas.domain.promotion.context.giveaway.GiveawayPromotion;
import christmas.domain.promotion.context.giveaway.impl.GiveawayByTotalAmountPromotion;

public enum GiveawayPromotionType {
    GIVEAWAY(GiveawayByTotalAmountPromotion.getInstance());

    private static final GiveawayPromotionType[] GIVEAWAY_PROMOTION_TYPES = values();
    private final GiveawayPromotion promotion;

    GiveawayPromotionType(GiveawayPromotion promotion) {
        this.promotion = promotion;
    }
}
