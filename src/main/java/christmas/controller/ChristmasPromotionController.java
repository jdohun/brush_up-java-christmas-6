package christmas.controller;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.enums.DiscountPromotionType;
import christmas.domain.promotion.enums.GiveawayPromotionType;
import christmas.dto.DiscountInfo;
import christmas.dto.GiveawayInfo;
import christmas.view.outputView.OutputView;

import java.util.List;

public class ChristmasPromotionController {
    private static final OutputView OUTPUT_VIEW = OutputView.getInstance();

    public void run(DecemberEventPlan decemberEventPlan) {

    }

    private void applyDiscountPromotion(DecemberEventPlan decemberEventPlan) {
        final List<DiscountInfo> discountInfos = DiscountPromotionType.applyPromotionTypes(decemberEventPlan);
    }

    private void applyGiveawayPromotion(DecemberEventPlan decemberEventPlan) {
        final List<GiveawayInfo> giveawayInfos = GiveawayPromotionType.applyPromotionTypes(decemberEventPlan);
    }
}
