package christmas.domain.promotion.strategy.dateCheckStrategy.impl;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;

@FunctionalInterface
public interface DecemberEventPlanConditionChecker {
    boolean isPlanSatisfyingCondition(DecemberEventPlan decemberEventPlan);
}
