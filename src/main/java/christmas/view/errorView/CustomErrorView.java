package christmas.view.errorView;

public class CustomErrorView {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    private CustomErrorView() {
    }

    public static CustomErrorView getInstance() {
        return Holder.ERROR_VIEW;
    }

    public void showErrorMessage(IllegalArgumentException illegalArgumentException) {
        System.out.println(ERROR_MESSAGE_PREFIX + illegalArgumentException.getMessage());
    }

    private class Holder {
        private static final CustomErrorView ERROR_VIEW = new CustomErrorView();
    }
}
