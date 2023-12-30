package christmas.domain.promotion.strategy.dateCheckStrategy.impl;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.fixture.DecemberEventPlanFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class DayOfWeekConditionTest {

    private void testDayOfWeekCondition(List<DecemberEventPlan> plans, Predicate<DecemberEventPlan> condition, boolean expected, String errorMessage) {
        // act
        final List<Boolean> plansResult = plans.stream()
                .map(condition::test)
                .collect(Collectors.toList());

        // assert
        assertThat(plansResult)
                .as(errorMessage)
                .containsOnly(expected);
    }

    @DisplayName("[false] 주말은 평일 조건을 통과하지 못한다.")
    @Test
    void isPlanSatisfyingWeekdayConditionByWeekendPlans() {
        // arrange & act & assert
        testDayOfWeekCondition(
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
        testDayOfWeekCondition(
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
        testDayOfWeekCondition(
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
        testDayOfWeekCondition(
                DecemberEventPlanFixture.WEEKEND.getPlans(),
                decemberEventPlan -> DayOfWeekCondition.IS_WEEKEND.isPlanSatisfyingCondition(decemberEventPlan),
                true,
                "모든 결과는 true 여야 합니다."
        );
    }
}