package christmas.domain.promotion.enums;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.context.giveaway.GiveawayPromotion;
import christmas.domain.promotion.context.giveaway.impl.GiveawayByTotalAmountPromotion;
import christmas.dto.GiveawayAndQuantityDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum GiveawayPromotionType {
    GIVEAWAY(GiveawayByTotalAmountPromotion.getInstance());

    private static final GiveawayPromotionType[] GIVEAWAY_PROMOTION_TYPES = values();
    private final GiveawayPromotion promotion;

    GiveawayPromotionType(GiveawayPromotion promotion) {
        this.promotion = promotion;
    }

    public static List<GiveawayAndQuantityDto> applyPromotionTypes(DecemberEventPlan decemberEventPlan) {
        List<GiveawayAndQuantityDto> giveawayAndQuantityDtoList = new ArrayList<>();

        for (GiveawayPromotionType giveawayPromotionType : GIVEAWAY_PROMOTION_TYPES) {
            Optional<GiveawayAndQuantityDto> optionalGiveawayAndQuantityDto = giveawayPromotionType.promotion.apply(decemberEventPlan);

            optionalGiveawayAndQuantityDto.ifPresent(giveawayAndQuantityDtoList::add);
        }

        return giveawayAndQuantityDtoList;
    }
}
