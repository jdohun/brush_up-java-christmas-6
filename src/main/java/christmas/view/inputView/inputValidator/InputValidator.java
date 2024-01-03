package christmas.view.inputView.inputValidator;

public final class InputValidator {
    private InputValidator() {
    }

    public static void isNotNull(String input) {
        if (null == input) {
            throw new IllegalArgumentException(InputValidatorErrorMessage.ERROR_NULL.getMessage());
        }
    }

    public static void isNotEmpty(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException(InputValidatorErrorMessage.ERROR_EMPTY.getMessage());
        }
    }
}
