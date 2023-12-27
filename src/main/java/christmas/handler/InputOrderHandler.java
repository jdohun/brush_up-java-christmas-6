package christmas.handler;

import christmas.domain.model.enums.menu.Menu;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static christmas.domain.model.classes.orderInfo.OrderInfoErrorMessage.ERROR_INVALID_ORDER;

public final class InputOrderHandler {
    private static final String ORDER_ITEM_DELIMITER = ",";
    private static final String ITEM_QUANTITY_DELIMITER = "-";

    private static final Pattern INVALID_ORDER_PATTERN = Pattern.compile("^\\s*" + ORDER_ITEM_DELIMITER + "|" + ORDER_ITEM_DELIMITER + "\\s*" + ORDER_ITEM_DELIMITER + "|" + ORDER_ITEM_DELIMITER + "\\s*$");
    private static final Pattern ORDER_ITEM_PATTERN = Pattern.compile("^[가-힣]+" + ITEM_QUANTITY_DELIMITER + "[1-9][0-9]*$");


    public static EnumMap<Menu, Integer> parseOrderInfo(String inputOrderInfo) {
        String[] splitOrderItems = validateAndSplitOrderInfo(inputOrderInfo);
        List<String> validatedOrderItems = validateSplitOrderItems(splitOrderItems);

        return validatedOrderItems.stream()
                .map(splitOrderItem())
                .collect(Collectors.toMap(
                        InputOrderHandler::extractMenuToKey,
                        InputOrderHandler::extractQuantityToValue,
                        InputOrderHandler::validateDuplicate,
                        InputOrderHandler::createEnumMap
                ));
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
                .map(menuWithQuantity -> validateOrderItemFormat(menuWithQuantity.trim()))
                .collect(Collectors.toList());
    }

    private static String validateOrderItemFormat(String orderItem) {
        Matcher matcher = ORDER_ITEM_PATTERN.matcher(orderItem);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER.getMessage());
        }
        return orderItem;
    }

    private static Function<String, String[]> splitOrderItem() {
        return orderItem -> orderItem.split(ITEM_QUANTITY_DELIMITER);
    }

    private static Menu extractMenuToKey(String[] parts) {
        validateExistsMenu(parts[0]);
        return Menu.findByName(parts[0]);
    }

    private static void validateExistsMenu(String menuName) {
        if (!Menu.isExistentMenu(menuName)) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER.getMessage());
        }
    }

    private static int extractQuantityToValue(String[] parts) {
        return Integer.parseInt(parts[1]);
    }

    private static Integer validateDuplicate(Integer existing, Integer replacement) {
        throw new IllegalArgumentException(ERROR_INVALID_ORDER.getMessage());
    }

    private static EnumMap<Menu, Integer> createEnumMap() {
        return new EnumMap<>(Menu.class);
    }
}
