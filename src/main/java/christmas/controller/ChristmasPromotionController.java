package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.view.outputView.OutputView;

public class ChristmasPromotionController {
    private static final OutputView OUTPUT_VIEW = OutputView.getInstance();

    public void run(DecemberEventPlan decemberEventPlan) {
        try {

        } finally {
            releaseResources();
        }
    }

    private void releaseResources() {
        Console.close();
    }
}
