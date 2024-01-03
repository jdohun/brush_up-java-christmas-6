package christmas.dto;

import java.util.List;

public record TotalDiscountAmount(int amount) {
    public static TotalDiscountAmount from(List<DiscountInfo> discountInfos) {
        int totalDiscountAmount = discountInfos.stream()
                .mapToInt(discountInfo -> discountInfo.discountAmount())
                .sum();
        return new TotalDiscountAmount(totalDiscountAmount);
    }
}
