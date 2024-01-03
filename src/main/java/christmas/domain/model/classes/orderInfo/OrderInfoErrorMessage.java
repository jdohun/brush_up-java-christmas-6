package christmas.domain.model.classes.orderInfo;

public enum OrderInfoErrorMessage {
    /**
     * 메뉴판에 없는 메뉴를 입력하는 경우
     * 메뉴의 개수에 1 이상의 숫자가 아닌 값이 있는 경우
     * 메뉴 형식이 예시와 다른 경우
     * 중복 메뉴를 입력한 경우
     */
    ERROR_INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ERROR_OVER_QUANTITY_OF_MENU("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요."),
    ERROR_ORDER_ONLY_BEVERAGE("음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.");

    private final String message;

    OrderInfoErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
