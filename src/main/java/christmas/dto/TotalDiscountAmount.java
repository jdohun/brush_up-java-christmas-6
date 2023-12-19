package christmas.dto;

import java.util.Arrays;

public record TotalDiscountAmount(int totalDiscountAmount) {
    public static TotalDiscountAmount of(DiscountInfo... discountInfos) {
        int totalDiscountAmount = Arrays.stream(discountInfos)
                .mapToInt(DiscountInfo::discountAmount)
                .sum();
        return new TotalDiscountAmount(totalDiscountAmount);
    }
}
