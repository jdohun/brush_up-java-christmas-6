package christmas.fixture;

import christmas.domain.model.classes.orderInfo.OrderInfo;
import christmas.domain.model.enums.menu.Menu;
import christmas.parser.InputOrderParser;

import java.util.EnumMap;

public enum OrderFixture {
    ALL_AROUND_1("양송이수프-1,티본스테이크-2,초코케이크-3,제로콜라-4"),
    ALL_AROUND_2("타파스-1,바비큐립-1,아이스크림-1,레드와인-1"),
    DESSERT_BEVERAGE("초코케이크-1,제로콜라-1"),
    ONLY_DESERT("아이스크림-1");

    private final String value;

    OrderFixture(String value) {
        this.value = value;
    }

    public OrderInfo toModel() {
        EnumMap<Menu, Integer> source = InputOrderParser.parseOrderInfo(value);
        return OrderInfo.from(source);
    }
}
