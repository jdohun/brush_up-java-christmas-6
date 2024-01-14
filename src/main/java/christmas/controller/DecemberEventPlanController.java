package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.classes.expectedVisitDay.ExpectedVisitDay;
import christmas.domain.model.classes.orderInfo.OrderInfo;
import christmas.domain.model.enums.menu.Menu;
import christmas.parser.InputDayOfMonthParser;
import christmas.parser.InputOrderParser;
import christmas.view.errorView.IllegalArgumentErrorView;
import christmas.view.inputView.InputView;
import christmas.view.outputView.OutputView;

import java.util.EnumMap;
import java.util.function.Supplier;

public class DecemberEventPlanController {
    private static final InputView INPUT_VIEW = InputView.getInstance();
    private static final OutputView OUTPUT_VIEW = OutputView.getInstance();
    private static final IllegalArgumentErrorView ILLEGAL_ARGUMENT_ERROR_VIEW = IllegalArgumentErrorView.getInstance();

    public DecemberEventPlan run() {
        try {
            OUTPUT_VIEW.printGreetings();
            return inputDecemberEventPlan();
        } finally {
            releaseResources();
        }
    }

    private int inputDayOfMonth() {
        return InputDayOfMonthParser.parseDayOfMonth(INPUT_VIEW.inputExpectedVisitDay());
    }

    private ExpectedVisitDay inputExpectedVisitDay() {
        return ExpectedVisitDay.from(inputDayOfMonth());
    }

    private OrderInfo inputOrderInfo() {
        return OrderInfo.from(parseInputOrderInfo());
    }

    private EnumMap<Menu, Integer> parseInputOrderInfo() {
        return InputOrderParser.parseOrderInfo(INPUT_VIEW.inputOrderInfo());
    }

    private DecemberEventPlan inputDecemberEventPlan() {
        ExpectedVisitDay expectedVisitDay = repeatUntilNoIllegalException(this::inputExpectedVisitDay);
        OrderInfo orderInfo = repeatUntilNoIllegalException(this::inputOrderInfo);
        return new DecemberEventPlan(expectedVisitDay, orderInfo);
    }

    private <T> T repeatUntilNoIllegalException(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException illegalArgumentException) {
                ILLEGAL_ARGUMENT_ERROR_VIEW.showErrorMessage(illegalArgumentException);
            } finally {
                OUTPUT_VIEW.separateLine();
            }
        }
    }

    private void releaseResources() {
        Console.close();
    }
}
