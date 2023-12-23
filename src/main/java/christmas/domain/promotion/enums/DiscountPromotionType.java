package christmas.domain.promotion.enums;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.classes.discountInfos.DiscountInfos;
import christmas.domain.promotion.context.discount.DiscountPromotion;
import christmas.domain.promotion.context.discount.impl.ChristmasDDayPromotion;
import christmas.domain.promotion.context.discount.impl.SpecialDayPromotion;
import christmas.domain.promotion.context.discount.impl.WeekdayPromotion;
import christmas.domain.promotion.context.discount.impl.WeekendPromotion;
import christmas.dto.DiscountInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum DiscountPromotionType {
    CHRISTMAS_D_DAY(ChristmasDDayPromotion.getInstance()),
    WEEKDAY(WeekdayPromotion.getInstance()),
    WEEKEND(WeekendPromotion.getInstance()),
    SPECIAL_DAY(SpecialDayPromotion.getInstance());

    private static final DiscountPromotionType[] DISCOUNT_PROMOTION_TYPES = DiscountPromotionType.values();
    private final DiscountPromotion promotion;

    DiscountPromotionType(DiscountPromotion promotion) {
        this.promotion = promotion;
    }

    public static DiscountInfos applyPromotionTypes(DecemberEventPlan decemberEventPlan) {
        final List<Optional<DiscountInfo>> appliedPromotionResults = Arrays.stream(DISCOUNT_PROMOTION_TYPES)
                .map(discountPromotionType -> discountPromotionType.promotion.apply(decemberEventPlan))
                .collect(Collectors.toList());

        final List<DiscountInfo> discountInfoList = collectValidDiscountInfos(appliedPromotionResults);

        return createDiscountInfos(discountInfoList);
    }

    private static List<DiscountInfo> collectValidDiscountInfos(List<Optional<DiscountInfo>> appliedDiscountResults) {
        return appliedDiscountResults.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private static DiscountInfos createDiscountInfos(final List<DiscountInfo> discountInfoList){
        return new DiscountInfos(discountInfoList);
    }
}
