package christmas.domain.policy;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.enums.AppliedEventPolicyName;
import christmas.domain.model.enums.Giveaway;
import christmas.dto.eventResult.EventResult;
import christmas.dto.eventResult.impl.GiveawayEventResultDto;

import java.util.Map;
import java.util.Optional;

/**
 * 할인 전 총주문 금액이 12만원 이상인지 확인 후
 * 혜택(증정 샴폐인)을 반환한다.
 * 혜택 정보(혜택 종류, 혜택 : 증정품)를 반환한다.
 */
class GiveawayEventPolicy {
    private static final int GIVEAWAY_APPLY_CRITERIA_AMOUNT = 120_000;

    private GiveawayEventPolicy() {
    }

    public static Optional<Map<AppliedEventPolicyName, EventResult>> applyEventPolicy(DecemberEventPlan decemberEventPlan) {
        if (isSatisfyingPrecondition(decemberEventPlan)) {
            Optional.of(Map.of(AppliedEventPolicyName.GIVEAWAY_EVENT, createGiveawayEventResult()));
        }
        return Optional.empty();
    }

    private static boolean isSatisfyingPrecondition(DecemberEventPlan decemberEventPlan) {
        return GIVEAWAY_APPLY_CRITERIA_AMOUNT <= decemberEventPlan.calculateTotalAmountBeforeDiscount();
    }

    private static EventResult createGiveawayEventResult() {
        return new GiveawayEventResultDto(Map.of(Giveaway.GIVEAWAY_CHAMPAGNE, 1));
    }
}
