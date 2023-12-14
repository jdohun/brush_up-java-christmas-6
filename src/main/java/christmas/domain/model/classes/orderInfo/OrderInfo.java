package christmas.domain.model.classes.orderInfo;

import christmas.domain.model.enums.menu.Menu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static christmas.domain.model.classes.orderInfo.OrderInfoErrorMessage.*;

public class OrderInfo {
    public static final String ORDER_ITEM_DELIMITER  = ",";
    public static final String ITEM_COUNT_DELIMITER  = "-";

    private static final Pattern ORDER_ITEM_PATTERN = Pattern.compile("^[가-힣]+" + ITEM_COUNT_DELIMITER  + "[1-9][0-9]*$");
    private static final Pattern INVALID_ORDER_PATTERN = Pattern.compile("^\\s*" + ORDER_ITEM_DELIMITER  + "|" + ORDER_ITEM_DELIMITER  + "\\s*" + ORDER_ITEM_DELIMITER  + "|" + ORDER_ITEM_DELIMITER  + "\\s*$");

    private static final int MAX_TOTAL_COUNT = 20;

    private final Map<Menu, Integer> orderInfo;

    private OrderInfo(Map<Menu, Integer> orderInfo) {
        this.orderInfo = Map.copyOf(orderInfo);
    }

    public static OrderInfo from(String inputOrderInfo) {
        Map<Menu, Integer> orderResult = validateAndMapOrderInfo(inputOrderInfo);
        return new OrderInfo(orderResult);
    }

    private static Map<Menu, Integer> validateAndMapOrderInfo(String inputOrderInfo) {
        String[] splitOrderItems = validateAndSplitOrderInfo(inputOrderInfo);
        List<String> validatedOrderItems = validateSplitOrderItems(splitOrderItems);
        return mapMenuAndCount(validatedOrderItems);
    }

    private static String[] validateAndSplitOrderInfo(String inputOrderInfo) {
        validateOrderInfoFormat(inputOrderInfo);
        return inputOrderInfo.split(ORDER_ITEM_DELIMITER);
    }

    private static void validateOrderInfoFormat(String inputOrderInfo) {
        Matcher matcher = INVALID_ORDER_PATTERN.matcher(inputOrderInfo);
        if (matcher.find()) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER.getMessage());
        }
    }

    private static List<String> validateSplitOrderItems(String[] splitOrders) {
        return Arrays.stream(splitOrders)
                .map(menuWithCount -> validateOrderItemFormat(menuWithCount.trim()))
                .collect(Collectors.toList());
    }

    private static String validateOrderItemFormat(String orderItem) {
        Matcher matcher = ORDER_ITEM_PATTERN.matcher(orderItem);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER.getMessage());
        }
        return orderItem;
    }

    private static Map<Menu, Integer> mapMenuAndCount(List<String> validatedOrderItems) {
        Map<Menu, Integer> orderResult = new HashMap<>();
        int totalItemCount = 0;

        for (String orderItem : validatedOrderItems) {
            String[] menuAndCount = orderItem.split(ITEM_COUNT_DELIMITER);
            String menuName = menuAndCount[0];

            Menu menu = validateOrderItem(menuName, orderResult);
            int itemCount = Integer.parseInt(menuAndCount[1]);

            validateTotalItemCount(totalItemCount, itemCount);

            orderResult.put(menu, itemCount);
            totalItemCount += itemCount;
        }

        validateContainsNonBeverageOrder(orderResult);
        return orderResult;
    }

    private static Menu validateOrderItem(String menuName, Map<Menu, Integer> result) {
        validateExistsMenu(menuName);
        Menu menu = Menu.findByName(menuName);
        validateDuplicateMenu(menu, result);
        return menu;
    }

    private static void validateExistsMenu(String menuName) {
        if (!Menu.isExistentMenu(menuName)) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER.getMessage());
        }
    }

    private static void validateDuplicateMenu(Menu menu, Map<Menu, Integer> result) {
        if (result.containsKey(menu)) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER.getMessage());
        }
    }

    private static void validateTotalItemCount(int totalItemCount, int itemCount) {
        if (totalItemCount + itemCount > MAX_TOTAL_COUNT) {
            throw new IllegalArgumentException(ERROR_OVER_COUNT_OF_MENU.getMessage());
        }
    }

    public static void validateContainsNonBeverageOrder(Map<Menu, Integer> orderResult) {
        boolean containsNonBeverage = orderResult.keySet().stream()
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
}
