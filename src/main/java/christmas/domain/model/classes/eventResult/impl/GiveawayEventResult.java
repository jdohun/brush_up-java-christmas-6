package christmas.domain.model.classes.eventResult.impl;

import christmas.domain.model.enums.Giveaway;
import christmas.domain.model.classes.eventResult.EventResult;

import java.util.Map;

public class GiveawayEventResult implements EventResult<Map<Giveaway, Integer>> {
    private final Map<Giveaway, Integer> giveaway;

    public GiveawayEventResult(Map<Giveaway, Integer> giveaway) {
        this.giveaway = Map.copyOf(giveaway);
    }

    @Override
    public Map<Giveaway, Integer> getResult() {
        return giveaway;
    }
}
