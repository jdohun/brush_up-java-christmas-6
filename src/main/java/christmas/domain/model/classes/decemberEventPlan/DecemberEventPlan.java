package christmas.domain.model.classes.decemberEventPlan;

import christmas.domain.model.classes.expectedVisitDay.DateBasedDiscountCalculator;
import christmas.domain.model.classes.expectedVisitDay.EventDateChecker;
import christmas.domain.model.classes.expectedVisitDay.ExpectedVisitDay;
import christmas.domain.model.classes.orderInfo.OrderInfo;
import christmas.dto.TotalAmount;

public class DecemberEventPlan {
    private final ExpectedVisitDay expectedVisitDay;
    private final OrderInfo orderInfo;
    private final TotalAmount totalAmountBeforeDiscount;

    public DecemberEventPlan(ExpectedVisitDay expectedVisitDay, OrderInfo orderInfo) {
        this.expectedVisitDay = expectedVisitDay;
        this.orderInfo = orderInfo;
        this.totalAmountBeforeDiscount = new TotalAmount(orderInfo.calculateTotalAmount());
    }

    public TotalAmount getTotalAmountBeforeDiscount() {
        return totalAmountBeforeDiscount;
    }

    public boolean isPlanWithinEventPeriod(EventDateChecker eventDateChecker) {
        return expectedVisitDay.isDateWithinEventPeriod(eventDateChecker);
    }

    public int calculateEventBenefitByDate(DateBasedDiscountCalculator dateBasedDiscountCalculator) {
        return expectedVisitDay.calculateEventBenefitByDate(dateBasedDiscountCalculator);
    }

    public int countMainMenus() {
        return orderInfo.countMainMenus();
    }

    public int countDessertMenus() {
        return orderInfo.countDessertMenus();
    }
}
