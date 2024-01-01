package christmas.fixture;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.classes.expectedVisitDay.ExpectedVisitDay;
import christmas.domain.model.classes.orderInfo.OrderInfo;
import christmas.domain.model.enums.menu.Menu;
import christmas.parser.InputOrderParser;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public enum OrderFixture {
    ALL_AROUND_BY_INCREASE_QUANTITY("양송이수프-1,티본스테이크-2,초코케이크-3,제로콜라-4"),
    ALL_AROUND_BY_1_QUANTITY("타파스-1,바비큐립-1,아이스크림-1,레드와인-1"),
    DESSERT_BEVERAGE("초코케이크-1,제로콜라-1"),
    ONLY_DESERT("아이스크림-1");

    private static final OrderFixture[] ORDER_FIXTURES = values();
    private final String value;

    OrderFixture(String value) {
        this.value = value;
    }

    public static List<DecemberEventPlan> toPlans() {
        final ExpectedVisitDay expectedVisitDay = ExpectedVisitDay.from(1);

        return Arrays.stream(ORDER_FIXTURES)
                .map(orderFixture -> new DecemberEventPlan(expectedVisitDay, orderFixture.toModel()))
                .collect(Collectors.toList());
    }

    public OrderInfo toModel() {
        EnumMap<Menu, Integer> source = InputOrderParser.parseOrderInfo(value);
        return OrderInfo.from(source);
    }

    public DecemberEventPlan toPlan() {
        final ExpectedVisitDay expectedVisitDay = ExpectedVisitDay.from(1);

        return new DecemberEventPlan(expectedVisitDay, this.toModel());
    }
}
