package christmas.view.preview;

import christmas.domain.model.classes.discountInfos.DiscountInfos;
import christmas.domain.model.classes.giveawayInofs.GiveawayInfos;
import christmas.domain.model.enums.Badge;
import christmas.dto.*;

import java.util.List;
import java.util.Optional;

public final class Preview {
    private Preview() {
    }

    public static Preview getInstance() {
        return Holder.PREVIEW;
    }

    public void printPreview(ChristmasPromotionPreview christmasPromotionPreview) {
        printPreviewTitle(christmasPromotionPreview.expectedVisitDay());
        printOrderInfo(christmasPromotionPreview.orderInfo());
        printTotalAmountBeforeDiscount(christmasPromotionPreview.totalAmount());
        printGiveawayInfos(christmasPromotionPreview.giveawayInfos());
        printBenefitInfo(christmasPromotionPreview.giveawayInfos(), christmasPromotionPreview.discountInfos());
        printTotalBenefitAmount(christmasPromotionPreview.totalBenefitAmount());
        printTotalAmountAfterDiscount(christmasPromotionPreview.totalAmount(), christmasPromotionPreview.totalDiscountAmount());
        printOptionalBadge(christmasPromotionPreview.optionalBadge());
    }

    private void printPreviewTitle(ExpectedVisitDayDto expectedVisitDayDto) {
        System.out.println(String.format(PreviewMessage.EVENT_PREVIEW_TITLE_FORMAT.getMessage(), expectedVisitDayDto.date().getDayOfMonth()));
    }

    private void printOrderInfo(List<MenuAndQuantityDto> orderInfo) {
        final String menuFormat = PreviewMessage.MENU_FORMAT.getMessage();

        System.out.println(PreviewMessage.ORDER_MENU_TITLE.getMessage());
        orderInfo.forEach(
                menuAndQuantityDto -> System.out.println(
                        String.format(
                                menuFormat,
                                menuAndQuantityDto.menu().getName(),
                                menuAndQuantityDto.quantity()
                        )));
        separateLine();
    }

    private void printTotalAmountBeforeDiscount(TotalAmount totalAmount) {
        System.out.println(PreviewMessage.TOTAL_PRICE_BEFORE_DISCOUNT_TITLE.getMessage());
        System.out.println(String.format(PreviewMessage.MONEY_FORMAT.getMessage(), totalAmount.amount()));
        separateLine();
    }

    private void printGiveawayInfos(GiveawayInfos giveawayInfos) {
        final String menuFormat = PreviewMessage.MENU_FORMAT.getMessage();
        final List<GiveawayInfo> giveawayInfoList = giveawayInfos.giveawayInfoList();

        System.out.println(PreviewMessage.GIVEAWAY_MENU_TITLE.getMessage());

        if (!giveawayInfoList.isEmpty()) {
            giveawayInfoList.forEach(
                    giveawayInfo -> System.out.println(
                            String.format(menuFormat, giveawayInfo.giveaway().getName(), giveawayInfo.quantity())
                    ));
        }

        if (giveawayInfoList.isEmpty()) {
            System.out.println(PreviewMessage.NONE.getMessage());
        }
        separateLine();
    }

    private void printBenefitInfo(GiveawayInfos giveawayInfos, DiscountInfos discountInfos) {
        final String benefitDetailsFormat = PreviewMessage.BENEFIT_DETAILS_FORMAT.getMessage();
        final List<GiveawayInfo> giveawayInfoList = giveawayInfos.giveawayInfoList();
        final List<DiscountInfo> discountInfoList = discountInfos.discountInfoList();

        System.out.println(PreviewMessage.BENEFIT_DETAILS_TITLE.getMessage());

        if (!discountInfoList.isEmpty()) {
            discountInfoList.forEach(
                    discountInfo -> System.out.println(
                            String.format(benefitDetailsFormat, discountInfo.promotionName().getEventName(), discountInfo.discountAmount())
                    ));
        }

        if (!giveawayInfoList.isEmpty()) {
            giveawayInfoList.forEach(
                    giveawayInfo -> System.out.println(
                            String.format(benefitDetailsFormat, giveawayInfo.promotionName().getEventName(), giveawayInfo.calculateGiveawayAmount())
                    ));
        }

        if (discountInfoList.isEmpty() && giveawayInfoList.isEmpty()) {
            System.out.println(PreviewMessage.NONE.getMessage());
        }
        separateLine();
    }

    private void printTotalBenefitAmount(TotalBenefitAmount totalBenefitAmount) {
        final String moneyFormat = (totalBenefitAmount.amount() > 0) ? PreviewMessage.NEGATIVE_MONEY_FORMAT.getMessage() : PreviewMessage.MONEY_FORMAT.getMessage();

        System.out.println(PreviewMessage.TOTAL_BENEFIT_AMOUNT_TITLE.getMessage());
        System.out.println(String.format(moneyFormat, totalBenefitAmount.amount()));
        separateLine();
    }

    private void printTotalAmountAfterDiscount(TotalAmount totalAmount, TotalDiscountAmount totalDiscountAmount) {
        System.out.println(PreviewMessage.ESTIMATED_PAYMENT_AMOUNT_AFTER_DISCOUNT_TITLE.getMessage());
        System.out.println(String.format(PreviewMessage.MONEY_FORMAT.getMessage(), totalAmount.amount() - totalDiscountAmount.amount()));
        separateLine();
    }

    private void printOptionalBadge(Optional<Badge> optionalBadge) {
        System.out.println(PreviewMessage.DECEMBER_EVENT_BADGE_TITLE.getMessage());
        System.out.println(optionalBadge.map(Badge::getName).orElse(PreviewMessage.NONE.getMessage()));
        separateLine();
    }

    private void separateLine() {
        System.out.println();
    }

    private class Holder {
        private static final Preview PREVIEW = new Preview();
    }
}
