package christmas.domain.promotion.strategy.dateCheckStrategy;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;

@FunctionalInterface
public interface DecemberEventPlanConditionChecker {
    boolean isPlanSatisfyingCondition(DecemberEventPlan decemberEventPlan);
}
