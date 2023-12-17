package christmas.domain.model.classes.decemberEventPlan;

import christmas.domain.promotion.strategy.discountStrategy.byDate.DateBasedDiscountStrategy;
import christmas.domain.promotion.strategy.dateCheckStrategy.DateCheckStrategy;
import christmas.domain.model.classes.expectedVisitDay.ExpectedVisitDay;
import christmas.domain.model.classes.orderInfo.OrderInfo;
import christmas.dto.DiscountAmount;
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

    public boolean isDateSatisfyingDateCondition(DateCheckStrategy dateCheckStrategy) {
        return expectedVisitDay.isSatisfyingCondition(dateCheckStrategy);
    }

    public DiscountAmount calculateDiscountAmountByDate(DateBasedDiscountStrategy dateBasedDiscountStrategy) {
        return new DiscountAmount(expectedVisitDay.calculateDiscountAmountByDate(dateBasedDiscountStrategy));
    }

    public int countMainMenus() {
        return orderInfo.countMainMenus();
    }

    public int countDessertMenus() {
        return orderInfo.countDessertMenus();
    }
}
