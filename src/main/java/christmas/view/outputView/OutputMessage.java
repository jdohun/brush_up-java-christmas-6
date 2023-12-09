package christmas.view.outputView;

enum OutputMessage {
    GREETINGS("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    EVENT_PREVIEW_TITLE_FORMAT("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),

    MENU_FORMAT("%s %d개"),
    MONEY_FORMAT("%,d원"),
    BENEFIT_DETAILS_FORMAT("%s: %,d원"),

    ORDER_MENU_TITLE("<주문 메뉴>"),
    TOTAL_PRICE_BEFORE_DISCOUNT_TITLE("<할인 전 총주문 금액>"),
    GIVEAWAY_MENU_TITLE("<증정 메뉴>"),
    BENEFIT_DETAILS_TITLE("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>"),
    ESTIMATED_PAYMENT_AMOUNT_AFTER_DISCOUNT_TITLE("<할인 후 예상 결제 금액>"),
    DECEMBER_EVENT_BADGE_TITLE("<할인 후 예상 결제 금액>");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
