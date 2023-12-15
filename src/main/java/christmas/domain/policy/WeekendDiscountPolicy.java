package christmas.domain.policy;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.enums.AppliedEventPolicyName;
import christmas.domain.model.classes.eventResult.DiscountAmount;
import christmas.domain.model.classes.eventResult.EventResult;
import christmas.domain.model.classes.eventResult.impl.DiscountEventResult;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

/**
 * 주말(금요일, 토요일)인지 확인 후
 * 메인 메뉴를 메뉴 1개당 2,023원 할인으로 계산하여
 * 혜택 정보(혜택 종류, 혜택 : 계산된 할인 금액)를 반환한다.
 */
class WeekendDiscountPolicy {
    public static final int WEEKEND_DISCOUNT_AMOUNT = 2_023;

    private WeekendDiscountPolicy() {
    }

    public static Optional<Map<AppliedEventPolicyName, EventResult>> applyEventPolicy(DecemberEventPlan decemberEventPlan) {
        if (isSatisfyingPrecondition(decemberEventPlan)) {
            return Optional.of(Map.of(AppliedEventPolicyName.WEEKEND_DISCOUNT, calculateDiscountEventResult(decemberEventPlan)));
        }
        return Optional.empty();
    }

    private static boolean isSatisfyingPrecondition(DecemberEventPlan decemberEventPlan) {
        return decemberEventPlan.isDateWithinEventPeriod(WeekendDiscountPolicy::isSatisfyingDatePrecondition);
    }

    private static boolean isSatisfyingDatePrecondition(LocalDate dateOfPlan) {
        return dateOfPlan.getDayOfWeek() == DayOfWeek.FRIDAY || dateOfPlan.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    private static EventResult calculateDiscountEventResult(DecemberEventPlan decemberEventPlan) {
        return new DiscountEventResult(new DiscountAmount(calculateEventBenefit(decemberEventPlan)));
    }

    private static int calculateEventBenefit(DecemberEventPlan decemberEventPlan) {
        return WEEKEND_DISCOUNT_AMOUNT * decemberEventPlan.countMainMenus();
    }
}
