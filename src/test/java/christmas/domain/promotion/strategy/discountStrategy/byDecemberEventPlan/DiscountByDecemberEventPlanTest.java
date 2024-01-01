package christmas.domain.promotion.strategy.discountStrategy.byDecemberEventPlan;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.classes.expectedVisitDay.ExpectedVisitDay;
import christmas.fixture.OrderFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountByDecemberEventPlanTest {

    private void testDiscountByDecemberEventPlan(final int discountAmount, DiscountByDecemberEventPlan discounter, List<Integer> expected) {
        // arrange
        final List<DecemberEventPlan> plans = provide();

        // act
        final List<Integer> results = IntStream.range(0, plans.size())
                .mapToObj(index -> discounter.calculateDiscountAmount(plans.get(index)))
                .collect(Collectors.toList());

        // assert
        assertThat(results).containsExactlyElementsOf(expected);
    }

    private List<DecemberEventPlan> provide() {
        final ExpectedVisitDay expectedVisitDay = ExpectedVisitDay.from(1);

        return Arrays.stream(OrderFixture.values())
                .map(orderFixture -> new DecemberEventPlan(expectedVisitDay, orderFixture.toModel()))
                .collect(Collectors.toList());
    }

    @DisplayName("메인 메뉴의 개수에 따라 할인 금액이 정해진다.")
    @Test
    void calculateDiscountAmountByMain() {
        // arrange
        final int DISCOUNT_AMOUNT = 2_023;
        List<Integer> expected = List.of(DISCOUNT_AMOUNT * 2, DISCOUNT_AMOUNT * 1, 0, 0);

        // act & assert
        testDiscountByDecemberEventPlan(
                DISCOUNT_AMOUNT,
                DiscountByDecemberEventPlan.BY_MAIN_COUNT,
                expected
        );
    }

    @DisplayName("디저트 메뉴의 개수에 따라 할인 금액이 정해진다.")
    @Test
    void calculateDiscountAmountDesert() {
        // arrange
        final int DISCOUNT_AMOUNT = 2_023;
        List<Integer> expected = List.of(DISCOUNT_AMOUNT * 3, DISCOUNT_AMOUNT * 1, DISCOUNT_AMOUNT * 1, DISCOUNT_AMOUNT * 1);

        // act & assert
        testDiscountByDecemberEventPlan(
                DISCOUNT_AMOUNT,
                DiscountByDecemberEventPlan.BY_DESSERT_COUNT,
                expected
        );
    }
}