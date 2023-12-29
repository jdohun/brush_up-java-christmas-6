package christmas.domain.model.classes.orderInfo;

import christmas.domain.model.enums.menu.Menu;
import christmas.dto.MenuAndQuantityDto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static christmas.domain.model.classes.orderInfo.OrderInfoErrorMessage.ERROR_ORDER_ONLY_BEVERAGE;
import static christmas.domain.model.classes.orderInfo.OrderInfoErrorMessage.ERROR_OVER_QUANTITY_OF_MENU;

public class OrderInfo {
    private static final int MAX_TOTAL_QUANTITY = 20;

    private final EnumMap<Menu, Integer> orderInfo;

    private OrderInfo(EnumMap<Menu, Integer> orderInfo) {
        this.orderInfo = orderInfo;
    }

    public static OrderInfo from(EnumMap<Menu, Integer> orderInfo) {
        validate(orderInfo);
        return new OrderInfo(orderInfo);
    }

    private static void validate(EnumMap<Menu, Integer> orderInfo) {
        validateContainsNonBeverageOrder(orderInfo);
        validateTotalItemQuantity(
                orderInfo.entrySet().stream()
                        .mapToInt(entry -> entry.getValue())
                        .sum()
        );
    }

    private static void validateTotalItemQuantity(int totalItemQuantity) {
        if (totalItemQuantity > MAX_TOTAL_QUANTITY) {
            throw new IllegalArgumentException(ERROR_OVER_QUANTITY_OF_MENU.getMessage());
        }
    }

    private static void validateContainsNonBeverageOrder(EnumMap<Menu, Integer> orderInfo) {
        boolean containsNonBeverage = orderInfo.keySet().stream()
                .anyMatch(menu -> !menu.isBeverage());

        if (!containsNonBeverage) {
            throw new IllegalArgumentException(ERROR_ORDER_ONLY_BEVERAGE.getMessage());
        }
    }

    public int calculateTotalAmount() {
        return orderInfo.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public int countMainMenus() {
        return orderInfo.entrySet().stream()
                .filter(entry -> entry.getKey().isMain())
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int countDessertMenus() {
        return orderInfo.entrySet().stream()
                .filter(entry -> entry.getKey().isDessert())
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public List<MenuAndQuantityDto> toDtoList() {
        return orderInfo.entrySet().stream()
                .map(entry -> new MenuAndQuantityDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
