package christmas.fixture;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.classes.orderInfo.OrderInfo;

import java.util.List;
import java.util.stream.Collectors;

public enum DecemberEventPlanFixtureByDay {
    WEEKDAY(ExpectedVisitDayFixture.WEEKDAY),
    WEEKEND(ExpectedVisitDayFixture.WEEKEND),
    SPECIAL_DAY(ExpectedVisitDayFixture.SPECIAL_DAYS),
    NOT_SPECIAL_DAY(ExpectedVisitDayFixture.NOT_SPECIAL_DAYS),
    UNTIL_CHRISTMAS(ExpectedVisitDayFixture.UNTIL_CHRISTMAS),
    AFTER_CHRISTMAS(ExpectedVisitDayFixture.AFTER_CHRISTMAS),
    MONTHLY_DECEMBER(ExpectedVisitDayFixture.MONTHLY_DECEMBER);

    private final List<DecemberEventPlan> plans;

    DecemberEventPlanFixtureByDay(ExpectedVisitDayFixture visitDayFixture) {
        final OrderInfo orderInfo = OrderFixture.ALL_AROUND_BY_INCREASE_QUANTITY.toModel();

        this.plans = visitDayFixture.getDays().stream()
                .map(expectedVisitDay -> new DecemberEventPlan(expectedVisitDay, orderInfo))
                .collect(Collectors.toList());
    }

    public List<DecemberEventPlan> getPlans() {
        return plans;
    }
}

