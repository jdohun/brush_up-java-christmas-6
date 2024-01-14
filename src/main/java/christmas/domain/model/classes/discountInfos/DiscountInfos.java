package christmas.domain.model.classes.discountInfos;

import christmas.dto.DiscountInfo;
import christmas.dto.TotalDiscountAmount;

import java.util.List;

public record DiscountInfos(List<DiscountInfo> discountInfoList) {
    public TotalDiscountAmount calculateTotalDiscountAmount() {
        if (discountInfoList.isEmpty()){
            return new TotalDiscountAmount(0);
        }
        return TotalDiscountAmount.from(discountInfoList);
    }
}
