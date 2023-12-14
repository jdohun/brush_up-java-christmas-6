package christmas.dto.eventResult.impl;

import christmas.domain.model.enums.Giveaway;
import christmas.dto.eventResult.EventResult;

import java.util.Map;

public class GiveawayEventResultDto implements EventResult<Map<Giveaway, Integer>> {
    private final Map<Giveaway, Integer> giveaway;

    public GiveawayEventResultDto(Map<Giveaway, Integer> giveaway) {
        this.giveaway = Map.copyOf(giveaway);
    }

    @Override
    public Map<Giveaway, Integer> getResult() {
        return giveaway;
    }
}
