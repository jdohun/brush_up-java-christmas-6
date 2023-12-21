package christmas.controller;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.promotion.context.discount.impl.ChristmasDDayPromotion;
import christmas.domain.promotion.context.discount.impl.SpecialDayPromotion;
import christmas.domain.promotion.context.discount.impl.WeekdayPromotion;
import christmas.domain.promotion.context.discount.impl.WeekendPromotion;
import christmas.domain.promotion.context.giveaway.impl.GiveawayByTotalAmountPromotion;
import christmas.dto.DiscountInfo;
import christmas.dto.GiveawayAndQuantityDto;
import christmas.view.outputView.OutputView;

import java.util.Optional;

public class ChristmasPromotionController {
    private static final OutputView OUTPUT_VIEW = OutputView.getInstance();

    private static final ChristmasDDayPromotion CHRISTMAS_D_DAY_PROMOTION = ChristmasDDayPromotion.getInstance();
    private static final WeekdayPromotion WEEKDAY_PROMOTION = WeekdayPromotion.getInstance();
    private static final WeekendPromotion WEEKEND_PROMOTION = WeekendPromotion.getInstance();
    private static final SpecialDayPromotion SPECIAL_DAY_PROMOTION = SpecialDayPromotion.getInstance();
    private static final GiveawayByTotalAmountPromotion GIVEAWAY_PROMOTION = GiveawayByTotalAmountPromotion.getInstance();

    public void run(DecemberEventPlan decemberEventPlan) {

    }

    private void applyDiscountPromotion(DecemberEventPlan decemberEventPlan) {
        final Optional<DiscountInfo> christmasPromotionResult = CHRISTMAS_D_DAY_PROMOTION.apply(decemberEventPlan);
        final Optional<DiscountInfo> weekdayPromotionResult = WEEKDAY_PROMOTION.apply(decemberEventPlan);
        final Optional<DiscountInfo> weekendPromotionResult = WEEKEND_PROMOTION.apply(decemberEventPlan);
        final Optional<DiscountInfo> specialPromotionResult = SPECIAL_DAY_PROMOTION.apply(decemberEventPlan);
    }

    private void applyGiveawayPromotion(DecemberEventPlan decemberEventPlan) {
        final Optional<GiveawayAndQuantityDto> giveawayPromotionResult = GIVEAWAY_PROMOTION.apply(decemberEventPlan);

    }
}
