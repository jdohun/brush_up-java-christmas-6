package christmas.domain.policy;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.enums.AppliedEventPolicyName;
import christmas.dto.eventResult.EventResult;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 총 주문 금액이 10,000원 이상 인지
 * 날짜가 12월 이내인지 확인 후
 * 다른 정책들에게 넘겨서 정책들로부터 받은 혜택의 종합 내역을 반환한다.
 */
public class DecemberEventApplyPolicy {
    private static final int EVENT_PERIOD_YEAR = 2023;
    private static final int EVENT_PERIOD_MONTH = 12;
    private static final int EVENT_PERIOD_DAY_BEGINS = 1;
    private static final int EVENT_PERIOD_DAY_ENDS = 31;

    private static final LocalDate EVENT_PERIOD_BEGINS = LocalDate.of(EVENT_PERIOD_YEAR, EVENT_PERIOD_MONTH, EVENT_PERIOD_DAY_BEGINS);
    private static final LocalDate EVENT_PERIOD_ENDS = LocalDate.of(EVENT_PERIOD_YEAR, EVENT_PERIOD_MONTH, EVENT_PERIOD_DAY_ENDS);

    private static final int EVENT_APPLY_CRITERIA_AMOUNT = 10_000;

    private DecemberEventApplyPolicy() {
    }

    public static Optional<Map<AppliedEventPolicyName, EventResult>> applyEventPolicy(DecemberEventPlan decemberEventPlan) {
        if (areConditionsSatisfied(decemberEventPlan)) {
            return Optional.of(applyEventPolicies(decemberEventPlan));
        }
        return Optional.empty();
    }

    private static boolean areConditionsSatisfied(DecemberEventPlan decemberEventPlan) {
        boolean isDayWithinEventPeriod = decemberEventPlan.isDateWithinEventPeriod(DecemberEventApplyPolicy::isDateWithinEventPeriod);
        boolean isTotalAmountWithinEventAmount = decemberEventPlan.isTotalAmountSatisfyingCondition(DecemberEventApplyPolicy::isSatisfyingTotalAmountPrecondition);
        return isDayWithinEventPeriod && isTotalAmountWithinEventAmount;
    }

    private static boolean isDateWithinEventPeriod(LocalDate dateOfPlan) {
        return !dateOfPlan.isBefore(EVENT_PERIOD_BEGINS) && !dateOfPlan.isAfter(EVENT_PERIOD_ENDS);
    }

    private static boolean isSatisfyingTotalAmountPrecondition(int totalAmount) {
        return EVENT_APPLY_CRITERIA_AMOUNT <= totalAmount;
    }

    private static Map<AppliedEventPolicyName, EventResult> applyEventPolicies(DecemberEventPlan decemberEventPlan) {
        return Stream.of(
                        ChristmasDDayDiscountPolicy.applyEventPolicy(decemberEventPlan),
                        WeekdayDiscountPolicy.applyEventPolicy(decemberEventPlan),
                        WeekendDiscountPolicy.applyEventPolicy(decemberEventPlan),
                        SpecialDayDiscountPolicy.applyEventPolicy(decemberEventPlan),
                        GiveawayEventPolicy.applyEventPolicy(decemberEventPlan)
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce(new HashMap<>(), (resultMap, map) -> {
                    resultMap.putAll(map);
                    return resultMap;
                });
    }
}
