package christmas.view.errorView;

public class ErrorView {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    private ErrorView() {
    }

    public static ErrorView getInstance() {
        return Holder.ERROR_VIEW;
    }

    public void showError(IllegalArgumentException illegalArgumentException) {
        System.out.println(ERROR_MESSAGE_PREFIX + illegalArgumentException.getMessage());
    }

    private class Holder {
        private static final ErrorView ERROR_VIEW = new ErrorView();
    }
}
