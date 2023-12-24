package christmas.view.outputView.preview;

import christmas.domain.model.classes.discountInfos.DiscountInfos;
import christmas.domain.model.classes.giveawayInofs.GiveawayInfos;
import christmas.domain.model.enums.Badge;
import christmas.dto.*;

import java.util.List;
import java.util.Optional;

import static christmas.view.outputView.preview.PreviewMessage.*;

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
        System.out.println(String.format(EVENT_PREVIEW_TITLE_FORMAT.getMessage(), expectedVisitDayDto.date().getDayOfMonth()));
    }

    private void printOrderInfo(List<MenuAndQuantityDto> orderInfo) {
        final String menuFormat = MENU_FORMAT.getMessage();

        System.out.println(ORDER_MENU_TITLE.getMessage());
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
        System.out.println(TOTAL_PRICE_BEFORE_DISCOUNT_TITLE.getMessage());
        System.out.println(String.format(MONEY_FORMAT.getMessage(), totalAmount.amount()));
        separateLine();
    }

    private void printGiveawayInfos(GiveawayInfos giveawayInfos) {
        final String menuFormat = MENU_FORMAT.getMessage();
        final List<GiveawayInfo> giveawayInfoList = giveawayInfos.giveawayInfoList();

        System.out.println(GIVEAWAY_MENU_TITLE.getMessage());

        if (!giveawayInfoList.isEmpty()) {
            giveawayInfoList.forEach(
                    giveawayInfo -> System.out.println(
                            String.format(menuFormat, giveawayInfo.giveaway().getName(), giveawayInfo.quantity())
                    ));
        }

        if (giveawayInfoList.isEmpty()) {
            System.out.println(NONE.getMessage());
        }
        separateLine();
    }

    private void printBenefitInfo(GiveawayInfos giveawayInfos, DiscountInfos discountInfos) {
        final String benefitDetailsFormat = BENEFIT_DETAILS_FORMAT.getMessage();
        final List<GiveawayInfo> giveawayInfoList = giveawayInfos.giveawayInfoList();
        final List<DiscountInfo> discountInfoList = discountInfos.discountInfoList();

        System.out.println(BENEFIT_DETAILS_TITLE.getMessage());

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
            System.out.println(NONE.getMessage());
        }
        separateLine();
    }

    private void printTotalBenefitAmount(TotalBenefitAmount totalBenefitAmount) {
        final String moneyFormat = (totalBenefitAmount.amount() > 0) ? NEGATIVE_MONEY_FORMAT.getMessage() : MONEY_FORMAT.getMessage();

        System.out.println(TOTAL_BENEFIT_AMOUNT_TITLE.getMessage());
        System.out.println(String.format(moneyFormat, totalBenefitAmount.amount()));
        separateLine();
    }

    private void printTotalAmountAfterDiscount(TotalAmount totalAmount, TotalDiscountAmount totalDiscountAmount) {
        System.out.println(ESTIMATED_PAYMENT_AMOUNT_AFTER_DISCOUNT_TITLE.getMessage());
        System.out.println(String.format(MONEY_FORMAT.getMessage(), totalAmount.amount() - totalDiscountAmount.amount()));
        separateLine();
    }

    private void printOptionalBadge(Optional<Badge> optionalBadge) {
        System.out.println(DECEMBER_EVENT_BADGE_TITLE.getMessage());
        System.out.println(optionalBadge.map(Badge::getName).orElse(NONE.getMessage()));
        separateLine();
    }

    private void separateLine() {
        System.out.println();
    }

    private class Holder {
        private static final Preview PREVIEW = new Preview();
    }
}
