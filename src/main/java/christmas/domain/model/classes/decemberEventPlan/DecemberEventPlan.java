package christmas.domain.model.classes.decemberEventPlan;

import christmas.domain.model.classes.expectedVisitDay.ExpectedVisitDay;
import christmas.domain.model.classes.orderInfo.OrderInfo;
import christmas.domain.promotion.strategy.dateCheckStrategy.DateCheckStrategy;
import christmas.domain.promotion.strategy.discountStrategy.byDate.DateBasedDiscountStrategy;
import christmas.dto.ExpectedVisitDayDto;
import christmas.dto.MenuAndQuantityDto;
import christmas.dto.TotalAmount;

import java.util.List;

public class DecemberEventPlan {
    private final ExpectedVisitDay expectedVisitDay;
    private final OrderInfo orderInfo;

    public DecemberEventPlan(ExpectedVisitDay expectedVisitDay, OrderInfo orderInfo) {
        this.expectedVisitDay = expectedVisitDay;
        this.orderInfo = orderInfo;
    }

    public TotalAmount getTotalAmountBeforeDiscount() {
        return new TotalAmount(orderInfo.calculateTotalAmount());
    }

    public boolean isDateSatisfyingDateCondition(DateCheckStrategy dateCheckStrategy) {
        return expectedVisitDay.isSatisfyingCondition(dateCheckStrategy);
    }

    public int calculateDiscountAmountByDate(DateBasedDiscountStrategy dateBasedDiscountStrategy) {
        return expectedVisitDay.calculateDiscountAmountByDate(dateBasedDiscountStrategy);
    }

    public int countMainMenus() {
        return orderInfo.countMainMenus();
    }

    public int countDessertMenus() {
        return orderInfo.countDessertMenus();
    }

    public ExpectedVisitDayDto getExpectedVisitDayDto() {
        return expectedVisitDay.toDto();
    }

    public List<MenuAndQuantityDto> getOrderInfoDtoList() {
        return orderInfo.toDtoList();
    }
}
