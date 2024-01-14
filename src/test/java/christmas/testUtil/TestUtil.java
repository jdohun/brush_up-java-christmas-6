package christmas.testUtil;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtil {
    public static void testCondition(List<DecemberEventPlan> plans, Predicate<DecemberEventPlan> condition, boolean expected, String errorMessage) {
        // act
        final List<Boolean> plansResult = plans.stream()
                .map(condition::test)
                .collect(Collectors.toList());

        // assert
        assertThat(plansResult)
                .as(errorMessage)
                .containsOnly(expected);
    }
}
