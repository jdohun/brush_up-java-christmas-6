package christmas.domain.promotion.strategy.dateCheckStrategy.impl;

import christmas.fixture.DecemberEventPlanFixture;
import christmas.testUtil.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecificDateConditionTest {

    @DisplayName("[true] 스페셜 날짜는 모두 스페셜 날짜 조건을 만족합니다.")
    @Test
    void testSpecialDayConditionBySpecialDay() {
        TestUtil.testCondition(
                DecemberEventPlanFixture.SPECIAL_DAY.getPlans(),
                decemberEventPlan -> SpecificDateCondition.SPECIAL_DATES.isPlanSatisfyingCondition(decemberEventPlan),
                true,
                "스페셜 날짜는 모두 스페셜 날짜 조건을 만족합니다."
        );
    }

    @DisplayName("[false] 스페셜 날짜가 아니라면 스페셜 날짜 조건을 불만족합니다.")
    @Test
    void testSpecialDayConditionByNotSpecialDay() {
        TestUtil.testCondition(
                DecemberEventPlanFixture.NOT_SPECIAL_DAY.getPlans(),
                decemberEventPlan -> SpecificDateCondition.SPECIAL_DATES.isPlanSatisfyingCondition(decemberEventPlan),
                false,
                "스페셜 날짜가 아니라면 스페셜 날짜 조건을 불만족합니다."
        );
    }
}