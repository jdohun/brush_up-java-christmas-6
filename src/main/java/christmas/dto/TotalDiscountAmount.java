package christmas.dto;

import java.util.Arrays;
import java.util.List;

public record TotalDiscountAmount(int amount) {
    public static TotalDiscountAmount of(DiscountInfo... discountInfos) {
        int totalDiscountAmount = Arrays.stream(discountInfos)
                .mapToInt(DiscountInfo::discountAmount)
                .sum();
        return new TotalDiscountAmount(totalDiscountAmount);
    }

    public static TotalDiscountAmount from(List<DiscountInfo> discountInfos) {
        int totalDiscountAmount = discountInfos.stream()
                .mapToInt(discountInfo -> discountInfo.discountAmount())
                .sum();
        return new TotalDiscountAmount(totalDiscountAmount);
    }

}
