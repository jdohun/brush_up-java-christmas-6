package christmas.domain.promotion.enums;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.context.giveaway.GiveawayPromotion;
import christmas.domain.promotion.context.giveaway.impl.GiveawayByTotalAmountPromotion;
import christmas.dto.GiveawayInfo;

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

    public static List<GiveawayInfo> applyPromotionTypes(DecemberEventPlan decemberEventPlan) {
        List<GiveawayInfo> giveawayInfos = new ArrayList<>();

        for (GiveawayPromotionType giveawayPromotionType : GIVEAWAY_PROMOTION_TYPES) {
            Optional<GiveawayInfo> optionalGiveawayAndQuantityDto = giveawayPromotionType.promotion.apply(decemberEventPlan);

            optionalGiveawayAndQuantityDto.ifPresent(giveawayInfos::add);
        }

        return giveawayInfos;
    }
}
