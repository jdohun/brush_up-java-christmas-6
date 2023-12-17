package christmas.view.outputView;

import static christmas.view.outputView.OutputMessage.GREETINGS;

public final class OutputView {
    private OutputView() {
    }

    public static OutputView getInstance() {
        return Holder.OUTPUT_VIEW;
    }

    public void separateLine() {
        System.out.println();
    }

    public void showGreetings() {
        System.out.println(GREETINGS.getMessage());
    }

    private class Holder {
        private static final OutputView OUTPUT_VIEW = new OutputView();
    }
}
