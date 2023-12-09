package christmas.domain.model.classes.orderInfo;

import christmas.domain.model.enums.Menu;

import java.util.Map;

public class OrderInfo {
    public static final String ORDER_DELIMITER = ",";
    public static final String ORDER_MENU_DELIMITER = "-";

    private final Map<Menu, Integer> orderInfo;

    private OrderInfo(Map<Menu, Integer> orderInfo) {
        this.orderInfo = Map.copyOf(orderInfo);
    }

    public static OrderInfo from(String inputOrderInfo) {
        return null;
    }
}
