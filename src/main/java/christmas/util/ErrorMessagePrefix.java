package christmas.util;

public enum ErrorMessagePrefix {
    ERROR_MESSAGE_PREFIX("[ERROR] ");

    private final String errorMessagePrefix;

    ErrorMessagePrefix(String errorMessagePrefix) {
        this.errorMessagePrefix = errorMessagePrefix;
    }

    public String getErrorMessagePrefix() {
        return errorMessagePrefix;
    }
}
