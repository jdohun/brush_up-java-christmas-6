package christmas.domain.promotion.strategy.dateCheckStrategy.impl;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.fixture.DecemberEventPlanFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class PeriodConditionTest {

    private void TestPeriodCondition(List<DecemberEventPlan> plans, Predicate<DecemberEventPlan> condition, boolean expected, String errorMessage) {
        // act
        final List<Boolean> plansResult = plans.stream()
                .map(condition::test)
                .collect(Collectors.toList());

        // assert
        assertThat(plansResult)
                .as(errorMessage)
                .containsOnly(expected);
    }

    @DisplayName("[true] 1~25일은 크리스마스 프로모션 기간 조건을 만족합니다.")
    @Test
    void testPeriodConditionByUntilChristmas() {
        // arrange & act & assert
        TestPeriodCondition(
                DecemberEventPlanFixture.UNTIL_CHRISTMAS.getPlans(),
                decemberEventPlan -> PeriodCondition.UNTIL_CHRISTMAS.isPlanSatisfyingCondition(decemberEventPlan),
                true,
                "1~25일은 크리스마스 프로모션 기간 조건을 만족합니다 : true"
        );
    }

    @DisplayName("[false] 26~31일은 크리스마스 프로모션 기간 조건을 불만족합니다.")
    @Test
    void testPeriodConditionByAfterChristmas() {
        // arrange & act & assert
        TestPeriodCondition(
                DecemberEventPlanFixture.AFTER_CHRISTMAS.getPlans(),
                decemberEventPlan -> PeriodCondition.UNTIL_CHRISTMAS.isPlanSatisfyingCondition(decemberEventPlan),
                false,
                "26~31일은 크리스마스 프로모션 기간 조건을 만족합니다 : false"
        );
    }

    @DisplayName("[true] 1~31일은 프로모션 기간 전제 조건을 만족합니다.")
    @Test
    void testPeriodConditionByDecember() {
        // arrange & act & assert
        TestPeriodCondition(
                DecemberEventPlanFixture.UNTIL_CHRISTMAS.getPlans(),
                decemberEventPlan -> PeriodCondition.MONTHLY_DECEMBER.isPlanSatisfyingCondition(decemberEventPlan),
                true,
                "1~25일은 크리스마스 프로모션 기간 전제 조건을 만족합니다 : true"
        );

        // arrange & act & assert
        TestPeriodCondition(
                DecemberEventPlanFixture.AFTER_CHRISTMAS.getPlans(),
                decemberEventPlan -> PeriodCondition.MONTHLY_DECEMBER.isPlanSatisfyingCondition(decemberEventPlan),
                true,
                "26~31일은 프로모션 기간 전제 조건을 만족합니다 : true"
        );
    }
}