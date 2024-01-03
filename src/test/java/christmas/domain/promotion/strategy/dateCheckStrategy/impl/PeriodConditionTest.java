package christmas.domain.promotion.strategy.dateCheckStrategy.impl;

import christmas.fixture.DecemberEventPlanFixtureByDay;
import christmas.testUtil.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PeriodConditionTest {

    @DisplayName("[true] 1~25일은 크리스마스 프로모션 기간 조건을 만족합니다.")
    @Test
    void testPeriodConditionByUntilChristmas() {
        // arrange & act & assert
        TestUtil.testCondition(
                DecemberEventPlanFixtureByDay.UNTIL_CHRISTMAS.getPlans(),
                decemberEventPlan -> PeriodCondition.UNTIL_CHRISTMAS.isPlanSatisfyingCondition(decemberEventPlan),
                true,
                "1~25일은 크리스마스 프로모션 기간 조건을 만족합니다 : true"
        );
    }

    @DisplayName("[false] 26~31일은 크리스마스 프로모션 기간 조건을 불만족합니다.")
    @Test
    void testPeriodConditionByAfterChristmas() {
        // arrange & act & assert
        TestUtil.testCondition(
                DecemberEventPlanFixtureByDay.AFTER_CHRISTMAS.getPlans(),
                decemberEventPlan -> PeriodCondition.UNTIL_CHRISTMAS.isPlanSatisfyingCondition(decemberEventPlan),
                false,
                "26~31일은 크리스마스 프로모션 기간 조건을 만족합니다 : false"
        );
    }

    @DisplayName("[true] 1~31일은 프로모션 기간 전제 조건을 만족합니다.")
    @Test
    void testPeriodConditionByDecember() {
        // arrange & act & assert
        TestUtil.testCondition(
                DecemberEventPlanFixtureByDay.MONTHLY_DECEMBER.getPlans(),
                decemberEventPlan -> PeriodCondition.MONTHLY_DECEMBER.isPlanSatisfyingCondition(decemberEventPlan),
                true,
                "1~31일은 크리스마스 프로모션 기간 전제 조건을 만족합니다 : true"
        );
    }
}