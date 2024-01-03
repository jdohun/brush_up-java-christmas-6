package christmas.dto;

import christmas.domain.promotion.enums.PromotionName;

public record DiscountInfo(
        PromotionName promotionName,
        int discountAmount
) {
}
