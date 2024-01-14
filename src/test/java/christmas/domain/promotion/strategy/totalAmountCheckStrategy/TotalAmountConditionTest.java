package christmas.domain.promotion.strategy.totalAmountCheckStrategy;

import christmas.dto.TotalAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class TotalAmountConditionTest {

    private void assertTotalAmountCondition(int totalAmountValue, TotalAmountCondition condition, boolean expected) {
        // arrange
        TotalAmount totalAmount = new TotalAmount(totalAmountValue);

        // act
        final boolean result = condition.isTotalAmountSufficient(totalAmount);

        // assert
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("[true] 총가격이 10,000원 이상이면 최소 금액 기준을 만족한다.")
    @ParameterizedTest
    @ValueSource(ints = {10_000, 10_001})
    void testMinimumOrderAmountBySufficientAmount(int sufficientAmount) {
        // arrange & act & assert
        assertTotalAmountCondition(sufficientAmount, TotalAmountCondition.MINIMUM_ORDER_AMOUNT, true);
    }

    @DisplayName("[false] 총가격이 10,000원 미만이면 최소 금액 기준을 불만족한다.")
    @Test
    void testMinimumOrderAmountByInSufficientAmount() {
        // arrange & act & assert
        assertTotalAmountCondition(9_999, TotalAmountCondition.MINIMUM_ORDER_AMOUNT, false);
    }

    @DisplayName("[true] 총가격이 120,000원 이상이면 증정 프로모션 금액 기준을 만족한다.")
    @ParameterizedTest
    @ValueSource(ints = {120_000, 120_001})
    void testGiveawayBySufficientAmount(int sufficientAmount) {
        // arrange & act & assert
        assertTotalAmountCondition(sufficientAmount, TotalAmountCondition.GIVEAWAY_EVENT_THRESHOLD, true);
    }

    @DisplayName("[false] 총가격이 120,000원 미만이면 증정 프로모션 금액 기준을 불만족한다.")
    @Test
    void testGiveawayByInSufficientAmount() {
        // arrange & act & assert
        assertTotalAmountCondition(111_999, TotalAmountCondition.GIVEAWAY_EVENT_THRESHOLD, false);
    }
}