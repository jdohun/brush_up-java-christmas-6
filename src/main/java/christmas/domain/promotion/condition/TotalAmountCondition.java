package christmas.domain.promotion.condition;

import christmas.dto.TotalAmount;

public enum TotalAmountCondition {
    MINIMUM_ORDER_AMOUNT(10_000),
    GIVEAWAY_EVENT_THRESHOLD(120_000),
    ;

    int amountCondition;

    TotalAmountCondition(int amountCondition) {
        this.amountCondition = amountCondition;
    }

    public boolean isTotalAmountSufficient(TotalAmount totalAmount) {
        return this.amountCondition <= totalAmount.amount();
    }
}
