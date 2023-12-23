package christmas.dto;

import christmas.domain.model.classes.discountInfos.DiscountInfos;
import christmas.domain.model.classes.giveawayInofs.GiveawayInfos;
import christmas.domain.model.enums.Badge;

import java.util.List;

public record ChristmasPromotionPreview(
        ExpectedVisitDayDto expectedVisitDay,
        List<MenuAndQuantityDto> orderInfo,
        TotalAmount totalAmount,
        GiveawayInfos giveawayInfos,
        DiscountInfos discountInfos,
        TotalGiveawayAmount totalGiveawayAmount,
        TotalDiscountAmount totalDiscountAmount,
        TotalBenefitAmount totalBenefitAmount,
        Badge badge
) {
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        ExpectedVisitDayDto expectedVisitDay;
        List<MenuAndQuantityDto> orderInfo;
        TotalAmount totalAmount;
        GiveawayInfos giveawayInfos;
        DiscountInfos discountInfos;
        TotalGiveawayAmount totalGiveawayAmount;
        TotalDiscountAmount totalDiscountAmount;
        TotalBenefitAmount totalBenefitAmount;
        Badge badge;
        private Builder() {
        }

        public Builder expectedVisitDay(ExpectedVisitDayDto expectedVisitDay) {
            this.expectedVisitDay = expectedVisitDay;
            return this;
        }

        public Builder orderInfo(List<MenuAndQuantityDto> orderInfo) {
            this.orderInfo = orderInfo;
            return this;
        }

        public Builder totalAmount(TotalAmount totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder giveawayInfo(GiveawayInfos giveawayInfos) {
            this.giveawayInfos = giveawayInfos;
            return this;
        }

        public Builder discountInfos(DiscountInfos discountInfos) {
            this.discountInfos = discountInfos;
            return this;
        }

        public Builder totalBenefitAmount(TotalBenefitAmount totalBenefitAmount) {
            this.totalBenefitAmount = totalBenefitAmount;
            return this;
        }

        public Builder totalGiveawayAmount(TotalGiveawayAmount totalGiveawayAmount) {
            this.totalGiveawayAmount = totalGiveawayAmount;
            return this;
        }

        public Builder totalDiscountAmount(TotalDiscountAmount totalDiscountAmount) {
            this.totalDiscountAmount = totalDiscountAmount;
            return this;
        }

        public Builder badge(Badge badge) {
            this.badge = badge;
            return this;
        }

        public ChristmasPromotionPreview build() {
            return new ChristmasPromotionPreview(
                    expectedVisitDay,
                    orderInfo,
                    totalAmount,
                    giveawayInfos,
                    discountInfos,
                    totalGiveawayAmount,
                    totalDiscountAmount,
                    totalBenefitAmount,
                    badge
            );
        }
    }
}
