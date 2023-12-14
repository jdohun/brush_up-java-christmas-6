package christmas.domain.policy;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.enums.AppliedEventPolicyName;
import christmas.dto.eventResult.DiscountAmountDto;
import christmas.dto.eventResult.EventResult;
import christmas.dto.eventResult.impl.DiscountEventResultDto;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

/**
 * 날짜가 12월 1일 에서 25일 인지 확인 후
 * 1일은 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
 * 혜택 정보(혜택 종류, 혜택 : 계산된 할인 금액)를 반환한다.
 */
class ChristmasDDayDiscountPolicy {

    private static final int DEFAULT_DISCOUNT_AMOUNT = 1_000;
    private static final int INCREASING_DISCOUNT_AMOUNT_PER_DAY = 100;

    private static final int EVENT_PERIOD_YEAR = 2023;
    private static final int EVENT_PERIOD_MONTH = 12;
    private static final int EVENT_PERIOD_DAY_BEGINS = 1;
    private static final int EVENT_PERIOD_DAY_ENDS = 25;

    private static final LocalDate EVENT_PERIOD_BEGINS = LocalDate.of(EVENT_PERIOD_YEAR, EVENT_PERIOD_MONTH, EVENT_PERIOD_DAY_BEGINS);
    private static final LocalDate EVENT_PERIOD_ENDS = LocalDate.of(EVENT_PERIOD_YEAR, EVENT_PERIOD_MONTH, EVENT_PERIOD_DAY_ENDS);

    private ChristmasDDayDiscountPolicy() {
    }

    public static Optional<Map<AppliedEventPolicyName, EventResult>> applyEventPolicy(DecemberEventPlan decemberEventPlan) {
        if (isSatisfyingPrecondition(decemberEventPlan)) {
            EventResult eventResult = calculateDiscountEventResult(decemberEventPlan);
            return Optional.of(Map.of(AppliedEventPolicyName.CHRISTMAS_D_DAY_DISCOUNT, eventResult));
        }
        return Optional.empty();
    }

    private static boolean isSatisfyingPrecondition(DecemberEventPlan decemberEventPlan) {
        return decemberEventPlan.isDateWithinEventPeriod(ChristmasDDayDiscountPolicy::isDateWithinChristmasDDayEventPeriod);
    }

    private static boolean isDateWithinChristmasDDayEventPeriod(LocalDate dateOfPlan) {
        return !dateOfPlan.isBefore(EVENT_PERIOD_BEGINS) && !dateOfPlan.isAfter(EVENT_PERIOD_ENDS);

    }

    private static EventResult calculateDiscountEventResult(DecemberEventPlan decemberEventPlan) {
        int discountAmount = calculateDiscountAmount(decemberEventPlan);
        return createDiscountEventResult(discountAmount);
    }

    private static int calculateDiscountAmount(DecemberEventPlan decemberEventPlan) {
        return decemberEventPlan.calculateEventBenefitByDate(ChristmasDDayDiscountPolicy::calculateEventBenefit);
    }

    private static int calculateEventBenefit(LocalDate dateOfPlan) {
        return DEFAULT_DISCOUNT_AMOUNT + INCREASING_DISCOUNT_AMOUNT_PER_DAY * (dateOfPlan.getDayOfMonth() - 1);
    }

    private static EventResult createDiscountEventResult(int discountAmount) {
        return new DiscountEventResultDto(new DiscountAmountDto(discountAmount));
    }
}
