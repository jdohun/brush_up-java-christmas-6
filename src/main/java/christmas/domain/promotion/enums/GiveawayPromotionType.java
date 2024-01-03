package christmas.domain.promotion.enums;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.classes.giveawayInofs.GiveawayInfos;
import christmas.domain.promotion.context.giveaway.GiveawayPromotion;
import christmas.domain.promotion.context.giveaway.impl.GiveawayByTotalAmountPromotion;
import christmas.dto.GiveawayInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum GiveawayPromotionType {
    GIVEAWAY(GiveawayByTotalAmountPromotion.getInstance());

    private static final GiveawayPromotionType[] GIVEAWAY_PROMOTION_TYPES = values();
    private final GiveawayPromotion promotion;

    GiveawayPromotionType(GiveawayPromotion promotion) {
        this.promotion = promotion;
    }

    public static GiveawayInfos applyPromotionTypes(DecemberEventPlan decemberEventPlan) {
        final List<Optional<GiveawayInfo>> appliedGiveawayResults = Arrays.stream(GIVEAWAY_PROMOTION_TYPES)
                .map(giveawayPromotionType -> giveawayPromotionType.promotion.apply(decemberEventPlan))
                .collect(Collectors.toList());

        final List<GiveawayInfo> giveawayInfoList = collectValidGiveawayInfoList(appliedGiveawayResults);

        return createGiveawayInfos(giveawayInfoList);
    }

    private static List<GiveawayInfo> collectValidGiveawayInfoList(List<Optional<GiveawayInfo>> appliedGiveawayResults) {
        return appliedGiveawayResults.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private static GiveawayInfos createGiveawayInfos(List<GiveawayInfo> giveawayInfoList) {
        return new GiveawayInfos(giveawayInfoList);
    }

}
