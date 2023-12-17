package christmas.dto;

import java.util.Arrays;

public record TotalDiscountAmount(int totalDiscountAmount) {
    public static TotalDiscountAmount of(DiscountAmount... discountAmounts) {
        int totalDiscountAmount = Arrays.stream(discountAmounts)
                .mapToInt(DiscountAmount::discountAmount)
                .sum();
        return new TotalDiscountAmount(totalDiscountAmount);
    }
}
