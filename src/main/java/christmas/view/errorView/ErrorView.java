package christmas.view.errorView;

public class ErrorView {
    private ErrorView() {
    }

    public static ErrorView getInstance() {
        return Holder.ERROR_VIEW;
    }

    public void showError(IllegalArgumentException illegalArgumentException) {
        System.out.println(illegalArgumentException.getMessage());
    }

    private class Holder {
        private static final ErrorView ERROR_VIEW = new ErrorView();
    }
}
