package christmas.domain.model.enums.menu;

public enum MenuErrorMessage {
    ERROR_MENU_NOT_FOUND("존재하지 않는 메뉴입니다.\n");

    private final String message;

    MenuErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
