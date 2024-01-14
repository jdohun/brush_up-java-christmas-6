package christmas.view.errorView;

public final class IllegalArgumentErrorView {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    private IllegalArgumentErrorView() {
    }

    public static IllegalArgumentErrorView getInstance() {
        return Holder.ILLEGAL_ARGUMENT_ERROR_VIEW;
    }

    public void showErrorMessage(IllegalArgumentException illegalArgumentException) {
        System.out.println(ERROR_MESSAGE_PREFIX + illegalArgumentException.getMessage());
    }

    private class Holder {
        private static final IllegalArgumentErrorView ILLEGAL_ARGUMENT_ERROR_VIEW = new IllegalArgumentErrorView();
    }
}
