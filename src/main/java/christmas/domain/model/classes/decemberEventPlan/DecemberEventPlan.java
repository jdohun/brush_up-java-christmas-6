package christmas.domain.model.classes.decemberEventPlan;

import christmas.domain.model.classes.expectedVisitDay.ExpectedVisitDay;
import christmas.domain.model.classes.orderInfo.OrderInfo;

public class DecemberEventPlan {

    private final ExpectedVisitDay expectedVisitDay;
    private final OrderInfo orderInfo;

    public DecemberEventPlan(ExpectedVisitDay expectedVisitDay, OrderInfo orderInfo) {
        this.expectedVisitDay = expectedVisitDay;
        this.orderInfo = orderInfo;
    }
}
