package christmas.dto;

import christmas.domain.model.enums.Giveaway;
import christmas.domain.promotion.enums.PromotionName;

public record GiveawayInfo(
        PromotionName promotionName,
        Giveaway giveaway,
        int quantity
) {
}
