package christmas.domain.model.enums.menu;

import static christmas.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;

public enum MenuErrorMessage {
    ERROR_MENU_NOT_FOUND("존재하지 않는 메뉴입니다.\n");

    private final String message;

    MenuErrorMessage(String message) {
        this.message = ERROR_MESSAGE_PREFIX.getErrorMessagePrefix() + message;
    }

    public String getMessage() {
        return message;
    }
}
