package christmas.domain.promotion.condition;

import christmas.dto.TotalAmount;

public enum TotalAmountCondition {
    MINIMUM_ORDER_AMOUNT(10_000),
    GIVEAWAY_EVENT_THRESHOLD(120_000),
    ;

    int preconditionAmount;

    TotalAmountCondition(int preconditionAmount) {
        this.preconditionAmount = preconditionAmount;
    }

    public boolean isSatisfyingPrecondition(TotalAmount totalAmount) {
        return this.preconditionAmount <= totalAmount.amount();
    }
}
