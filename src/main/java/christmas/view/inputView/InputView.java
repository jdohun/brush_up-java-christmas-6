package christmas.view.inputView;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.inputView.inputValidator.InputValidator;

import static christmas.view.inputView.InputMessage.INPUT_EXPECTED_VISIT_DAY;
import static christmas.view.inputView.InputMessage.INPUT_ORDER;

public final class InputView {
    private InputView() {
    }

    public static InputView getInstance() {
        return Holder.INPUT_VIEW;
    }

    public String inputExpectedVisitDay() {
        System.out.println(INPUT_EXPECTED_VISIT_DAY.getMessage());
        String expectedVisitDay = Console.readLine();
        validateInput(expectedVisitDay);
        return expectedVisitDay;
    }

    public String inputOrderInfo() {
        System.out.println(INPUT_ORDER.getMessage());
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
