package christmas.domain.model.classes.eventResult.impl;

import christmas.domain.model.classes.eventResult.DiscountAmount;
import christmas.domain.model.classes.eventResult.EventResult;

public class DiscountEventResult implements EventResult<DiscountAmount> {
    private final DiscountAmount discountAmount;

    public DiscountEventResult(DiscountAmount discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public DiscountAmount getResult() {
        return discountAmount;
    }
}