package christmas.domain.model.classes.decemberEventPlan;

import christmas.domain.model.classes.expectedVisitDay.DateBasedDiscountCalculator;
import christmas.domain.model.classes.expectedVisitDay.EventDateChecker;
import christmas.domain.model.classes.expectedVisitDay.ExpectedVisitDay;
import christmas.domain.model.classes.orderInfo.DiscountCriteriaChecker;
import christmas.domain.model.classes.orderInfo.OrderInfo;

public class DecemberEventPlan {

    private final ExpectedVisitDay expectedVisitDay;
    private final OrderInfo orderInfo;

    public DecemberEventPlan(ExpectedVisitDay expectedVisitDay, OrderInfo orderInfo) {
        this.expectedVisitDay = expectedVisitDay;
        this.orderInfo = orderInfo;
    }

    public int calculateTotalAmountBeforeDiscount() {
        return orderInfo.calculateTotalAmount();
    }

    public boolean isDateWithinEventPeriod(EventDateChecker eventDateChecker) {
        return expectedVisitDay.isDateWithinEventPeriod(eventDateChecker);
    }

    public boolean isTotalAmountSatisfyingCondition(DiscountCriteriaChecker discountCriteriaChecker) {
        return discountCriteriaChecker.isSatisfied(calculateTotalAmountBeforeDiscount());
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
