package christmas;

import christmas.controller.ChristmasPromotionController;
import christmas.controller.DecemberEventPlanController;
import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;

public class Application {
    public static void main(String[] args) {
        final DecemberEventPlanController decemberEventPlanController = new DecemberEventPlanController();
        final ChristmasPromotionController christmasPromotionController = new ChristmasPromotionController();
        final DecemberEventPlan decemberEventPlan = decemberEventPlanController.run();
        christmasPromotionController.run(decemberEventPlan);
    }
}
