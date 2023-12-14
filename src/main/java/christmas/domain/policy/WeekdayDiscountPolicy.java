package christmas.domain.policy;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.enums.AppliedEventPolicyName;
import christmas.dto.eventResult.DiscountAmountDto;
import christmas.dto.eventResult.EventResult;
import christmas.dto.eventResult.impl.DiscountEventResultDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

/**
 * 평일(일요일~목요일) || !주말(금요일, 토요일)인지 확인 후
 * 디저트 메뉴를 메뉴 1개당 2,023원 할인으로 계산하여
 * 혜택 정보(혜택 종류, 혜택 : 계산된 할인 금액)를 반환한다.
 */
class WeekdayDiscountPolicy {
    public static final int WEEKDAY_DISCOUNT_AMOUNT = 2_023;

    private WeekdayDiscountPolicy() {
    }

    public static Optional<Map<AppliedEventPolicyName, EventResult>> applyEventPolicy(DecemberEventPlan decemberEventPlan) {
        if (isSatisfyingPrecondition(decemberEventPlan)) {
            return Optional.of(Map.of(AppliedEventPolicyName.WEEKDAY_DISCOUNT, calculateDiscountEventResult(decemberEventPlan)));
        }
        return Optional.empty();
    }

    private static boolean isSatisfyingPrecondition(DecemberEventPlan decemberEventPlan) {
        return decemberEventPlan.isDateWithinEventPeriod(WeekdayDiscountPolicy::isSatisfyingDatePrecondition);
    }

    private static boolean isSatisfyingDatePrecondition(LocalDate dateOfPlan) {
        return dateOfPlan.getDayOfWeek() != DayOfWeek.FRIDAY || dateOfPlan.getDayOfWeek() != DayOfWeek.SATURDAY;
    }

    private static EventResult calculateDiscountEventResult(DecemberEventPlan decemberEventPlan) {
        return new DiscountEventResultDto(new DiscountAmountDto(calculateEventBenefit(decemberEventPlan)));
    }

    private static int calculateEventBenefit(DecemberEventPlan decemberEventPlan) {
        return WEEKDAY_DISCOUNT_AMOUNT * decemberEventPlan.countDessertMenus();
    }
}
