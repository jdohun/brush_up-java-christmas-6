package christmas.dto;

public record TotalBenefitAmount(int amount) {
    public static TotalBenefitAmount of(TotalDiscountAmount totalDiscountAmount, TotalGiveawayAmount totalGiveawayAmount) {
        int totalBenefitAmount = totalDiscountAmount.amount() + totalGiveawayAmount.amount();
        return new TotalBenefitAmount(totalBenefitAmount);
    }
}
