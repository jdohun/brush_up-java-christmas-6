package christmas.domain.policy;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.enums.AppliedEventPolicyName;
import christmas.domain.model.classes.eventResult.DiscountAmount;
import christmas.domain.model.classes.eventResult.EventResult;
import christmas.domain.model.classes.eventResult.impl.DiscountEventResult;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * 여기서는 별(3,10,17,24,25,31)이 있는 날짜인지 확인 후
 * 혜택 정보(혜택 종류, 혜택 : 계산된 할인 금액)를 반환한다.
 */
class SpecialDayDiscountPolicy {
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1_000;
    private static final Set<Integer> SPECIAL_DAYS_OF_MONTH = Set.of(3, 10, 17, 24, 25, 31);

    private SpecialDayDiscountPolicy() {
    }

    public static Optional<Map<AppliedEventPolicyName, EventResult>> applyEventPolicy(DecemberEventPlan decemberEventPlan) {
        if (isSatisfyingPrecondition(decemberEventPlan)) {
            return Optional.of(Map.of(AppliedEventPolicyName.SPECIAL_DAY_DISCOUNT, calculateDiscountEventResult()));
        }
        return Optional.empty();
    }

    private static boolean isSatisfyingPrecondition(DecemberEventPlan decemberEventPlan) {
        return decemberEventPlan.isDateWithinEventPeriod(SpecialDayDiscountPolicy::isSatisfyingDatePrecondition);
    }

    private static boolean isSatisfyingDatePrecondition(LocalDate dateOfPlan) {
        return SPECIAL_DAYS_OF_MONTH.contains(dateOfPlan.getDayOfMonth());
    }

    private static EventResult calculateDiscountEventResult() {
        return new DiscountEventResult(new DiscountAmount(SPECIAL_DISCOUNT_AMOUNT));
    }
}
