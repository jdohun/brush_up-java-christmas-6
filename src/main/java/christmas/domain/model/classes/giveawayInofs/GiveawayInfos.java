package christmas.domain.model.classes.giveawayInofs;

import christmas.dto.GiveawayInfo;
import christmas.dto.TotalGiveawayAmount;

import java.util.List;

public record GiveawayInfos(List<GiveawayInfo> giveawayInfoList) {
    public TotalGiveawayAmount calculateTotalGiveawayAmount() {
        if (giveawayInfoList.isEmpty()) {
            return new TotalGiveawayAmount(0);
        }
        return TotalGiveawayAmount.from(giveawayInfoList);
    }
}
