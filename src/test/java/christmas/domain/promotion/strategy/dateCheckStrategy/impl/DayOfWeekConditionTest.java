package christmas.domain.promotion.strategy.dateCheckStrategy.impl;

import christmas.fixture.DecemberEventPlanFixture;
import christmas.testUtil.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DayOfWeekConditionTest {

    @DisplayName("[false] 주말은 평일 조건을 통과하지 못한다.")
    @Test
    void isPlanSatisfyingWeekdayConditionByWeekendPlans() {
        // arrange & act & assert
        TestUtil.testCondition(
                DecemberEventPlanFixture.WEEKEND.getPlans(),
                decemberEventPlan -> DayOfWeekCondition.IS_WEEKDAY.isPlanSatisfyingCondition(decemberEventPlan),
                false,
                "모든 결과는 false 여야 합니다."
        );
    }

    @DisplayName("[true] 평일은 평일 조건을 통과한다.")
    @Test
    void isPlanSatisfyingWeekdayConditionByWeekdayPlans() {
        // arrange & act & assert
        TestUtil.testCondition(
                DecemberEventPlanFixture.WEEKDAY.getPlans(),
                decemberEventPlan -> DayOfWeekCondition.IS_WEEKDAY.isPlanSatisfyingCondition(decemberEventPlan),
                true,
                "모든 결과는 true 여야 합니다."
        );
    }

    @DisplayName("[false] 평일은 주말 조건을 통과하지 못한다.")
    @Test
    void isPlanSatisfyingWeekendConditionByWeekdayPlans() {
        // arrange & act & assert
        TestUtil.testCondition(
                DecemberEventPlanFixture.WEEKDAY.getPlans(),
                decemberEventPlan -> DayOfWeekCondition.IS_WEEKEND.isPlanSatisfyingCondition(decemberEventPlan),
                false,
                "모든 결과는 false 여야 합니다."
        );
    }

    @DisplayName("[true] 주말은 주말 조건을 통과한다.")
    @Test
    void isPlanSatisfyingWeekendConditionByWeekendPlans() {
        // arrange & act & assert
        TestUtil.testCondition(
                DecemberEventPlanFixture.WEEKEND.getPlans(),
                decemberEventPlan -> DayOfWeekCondition.IS_WEEKEND.isPlanSatisfyingCondition(decemberEventPlan),
                true,
                "모든 결과는 true 여야 합니다."
        );
    }
}