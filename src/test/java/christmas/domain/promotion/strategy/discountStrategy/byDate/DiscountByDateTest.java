package christmas.domain.promotion.strategy.discountStrategy.byDate;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.fixture.DecemberEventPlanFixtureByDay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountByDateTest {

    @DisplayName("1일 1000원을 시작으로 2일부터 1100원... 100원씩 추가된 할인 금액을 반환합니다.")
    @Test
    void calculateDiscountAmount() {
        // arrange
        final List<DecemberEventPlan> plans = DecemberEventPlanFixtureByDay.MONTHLY_DECEMBER.getPlans();

        final int initialAmount = 1000;
        final int increment = 100;

        final List<Integer> expectedResults = IntStream.iterate(initialAmount, i -> i + increment)
                .limit(plans.size())
                .boxed()
                .collect(Collectors.toList());

        // act
        final List<Integer> results = IntStream.range(0, plans.size())
                .mapToObj(index -> DiscountByDate.CHRISTMAS_D_DAY.calculateDiscountAmount(plans.get(index)))
                .collect(Collectors.toList());

        // assert
        assertThat(results)
                .containsExactlyElementsOf(expectedResults);
    }
}