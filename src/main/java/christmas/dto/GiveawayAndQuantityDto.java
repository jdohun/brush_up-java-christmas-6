package christmas.dto;

import christmas.domain.model.enums.Giveaway;

public record GiveawayAndQuantityDto(
        Giveaway giveaway,
        Integer quantity
) {
}
