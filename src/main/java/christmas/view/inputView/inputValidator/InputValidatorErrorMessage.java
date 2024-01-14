package christmas.view.inputView.inputValidator;

public enum InputValidatorErrorMessage {
    ERROR_NULL("null 을 입력할 수 없습니다."),
    ERROR_EMPTY("빈 문자열을 입력할 수 없습니다.");

    private final String message;

    InputValidatorErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
