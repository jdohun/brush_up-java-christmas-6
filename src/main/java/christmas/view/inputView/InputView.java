package christmas.view.inputView;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.inputView.inputValidator.InputValidator;

public final class InputView {
    private InputView() {
    }

    public static InputView getInstance() {
        return Holder.INPUT_VIEW;
    }

    public String inputExpectedVisitDay() {
        System.out.println(InputMessage.INPUT_EXPECTED_VISIT_DAY);
        String expectedVisitDay = Console.readLine();
        validateInput(expectedVisitDay);
        return expectedVisitDay;
    }

    public String inputOrderInfo() {
        System.out.println(InputMessage.INPUT_ORDER);
        String order = Console.readLine();
        validateInput(order);
        return order;
    }

    private void validateInput(String input) {
        InputValidator.isNotNull(input);
        InputValidator.isNotEmpty(input);
    }

    private class Holder {
        private static final InputView INPUT_VIEW = new InputView();
    }
}
