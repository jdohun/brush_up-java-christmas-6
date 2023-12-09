package christmas.controller;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.classes.expectedVisitDay.ExpectedVisitDay;
import christmas.domain.model.classes.orderInfo.OrderInfo;
import christmas.view.inputView.InputView;
import christmas.view.outputView.OutputView;

import java.util.function.Supplier;

public class ChristmasPromotionController {
    private static final InputView INPUT_VIEW = InputView.getInstance();
    private static final OutputView OUTPUT_VIEW = OutputView.getInstance();

    public void run() {
        OUTPUT_VIEW.showGreetings();
        DecemberEventPlan decemberEventPlan = inputDecemberEventPlan();

    }

    private ExpectedVisitDay inputExpectedVisitDay() {
        return ExpectedVisitDay.from(INPUT_VIEW.inputExpectedVisitDay());
    }

    private OrderInfo inputOrderInfo() {
        String inputOrderInfo = INPUT_VIEW.inputOrderInfo();
        return OrderInfo.from(inputOrderInfo);
    }

    private DecemberEventPlan inputDecemberEventPlan() {
        ExpectedVisitDay expectedVisitDay = repeatUntilNoException(this::inputExpectedVisitDay);
        OrderInfo orderInfo = repeatUntilNoException(this::inputOrderInfo);
        return new DecemberEventPlan(expectedVisitDay, orderInfo);
    }

    private <T> T repeatUntilNoException(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
