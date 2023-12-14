package christmas.dto.eventResult.impl;

import christmas.dto.eventResult.DiscountAmountDto;
import christmas.dto.eventResult.EventResult;

public class DiscountEventResultDto implements EventResult<DiscountAmountDto> {
    private final DiscountAmountDto discountAmount;

    public DiscountEventResultDto(DiscountAmountDto discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public DiscountAmountDto getResult() {
        return discountAmount;
    }
}