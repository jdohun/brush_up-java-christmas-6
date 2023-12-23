package christmas.dto;

import java.util.List;

public record TotalGiveawayAmount(int amount) {
    public static TotalGiveawayAmount from(List<GiveawayInfo> giveawayInfos) {
        int totalGiveawayAmount = giveawayInfos.stream()
                .mapToInt(giveawayInfo -> giveawayInfo.quantity() * giveawayInfo.giveaway().getPrice())
                .sum();

        return new TotalGiveawayAmount(totalGiveawayAmount);
    }
}
