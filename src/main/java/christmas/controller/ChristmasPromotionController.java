package christmas.controller;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.classes.discountInfos.DiscountInfos;
import christmas.domain.model.classes.giveawayInofs.GiveawayInfos;
import christmas.domain.model.enums.Badge;
import christmas.domain.promotion.enums.DiscountPromotionType;
import christmas.domain.promotion.enums.GiveawayPromotionType;
import christmas.dto.ChristmasPromotionPreview;
import christmas.dto.TotalBenefitAmount;
import christmas.dto.TotalDiscountAmount;
import christmas.dto.TotalGiveawayAmount;
import christmas.view.outputView.preview.Preview;

public class ChristmasPromotionController {
    private static final Preview PREVIEW = Preview.getInstance();

    public void run(DecemberEventPlan decemberEventPlan) {
        final DiscountInfos discountInfos = applyDiscountPromotion(decemberEventPlan);
        final GiveawayInfos giveawayInfos = applyGiveawayPromotion(decemberEventPlan);
        final ChristmasPromotionPreview christmasPromotionPreview = createChristmasPromotionPreview(decemberEventPlan, giveawayInfos, discountInfos);
        PREVIEW.printPreview(christmasPromotionPreview);
    }

    private DiscountInfos applyDiscountPromotion(DecemberEventPlan decemberEventPlan) {
        return DiscountPromotionType.applyPromotionTypes(decemberEventPlan);
    }

    private GiveawayInfos applyGiveawayPromotion(DecemberEventPlan decemberEventPlan) {
        return GiveawayPromotionType.applyPromotionTypes(decemberEventPlan);
    }

    private ChristmasPromotionPreview createChristmasPromotionPreview(DecemberEventPlan decemberEventPlan, GiveawayInfos giveawayInfos, DiscountInfos discountInfos) {
        final TotalDiscountAmount totalDiscountAmount = discountInfos.calculateTotalDiscountAmount();
        final TotalGiveawayAmount totalGiveawayAmount = giveawayInfos.calculateTotalGiveawayAmount();
        final TotalBenefitAmount totalBenefitAmount = TotalBenefitAmount.of(totalDiscountAmount, totalGiveawayAmount);
        final Badge badge = Badge.getByTotalBenefitAmount(totalBenefitAmount);

        return ChristmasPromotionPreview.builder()
                .expectedVisitDay(decemberEventPlan.getExpectedVisitDayDto())
                .orderInfo(decemberEventPlan.getOrderInfoDtoList())
                .totalAmount(decemberEventPlan.getTotalAmountBeforeDiscount())
                .giveawayInfo(giveawayInfos)
                .discountInfos(discountInfos)
                .totalGiveawayAmount(totalGiveawayAmount)
                .totalDiscountAmount(totalDiscountAmount)
                .totalBenefitAmount(totalBenefitAmount)
                .badge(badge)
                .build();
    }
}
