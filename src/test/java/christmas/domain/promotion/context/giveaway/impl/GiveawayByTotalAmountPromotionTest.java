package christmas.domain.promotion.context.giveaway.impl;

import christmas.domain.model.classes.decemberEventPlan.DecemberEventPlan;
import christmas.domain.model.enums.Giveaway;
import christmas.domain.promotion.context.giveaway.GiveawayPromotion;
import christmas.domain.promotion.enums.PromotionName;
import christmas.dto.GiveawayInfo;
import christmas.fixture.OrderFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class GiveawayByTotalAmountPromotionTest {

    final static GiveawayPromotion GIVEAWAY_PROMOTION = GiveawayByTotalAmountPromotion.getInstance();

    @DisplayName("적용 가능한 경우 증정품 정보 반환")
    @ParameterizedTest
    @EnumSource(value = OrderFixture.class, names = {"ALL_AROUND_BY_INCREASE_QUANTITY", "ALL_AROUND_BY_1_QUANTITY"})
    void apply_GiveawayApplicable_ReturnsGiveawayInfo(OrderFixture orderFixture) {
        // Arrange
        DecemberEventPlan decemberEventPlan = orderFixture.toPlan();

        // Act
        Optional<GiveawayInfo> result = GIVEAWAY_PROMOTION.apply(decemberEventPlan);

        // Assert
        assertThat(result).isPresent();

        final GiveawayInfo giveawayInfo = result.get();

        assertThat(giveawayInfo)
                .extracting("promotionName", "giveaway", "quantity")
                .containsExactly(PromotionName.GIVEAWAY_EVENT, Giveaway.GIVEAWAY_CHAMPAGNE, 1);
    }

    @DisplayName("적용 불가능한 경우 빈 Optional 반환")
    @ParameterizedTest
    @EnumSource(value = OrderFixture.class, names = {"DESSERT_BEVERAGE", "ONLY_DESERT"})
    void apply_GiveawayNotApplicable_ReturnsEmptyOptional(OrderFixture orderFixture) {
        // Arrange
        DecemberEventPlan decemberEventPlan = orderFixture.toPlan();

        // Act
        Optional<GiveawayInfo> result = GIVEAWAY_PROMOTION.apply(decemberEventPlan);

        // Assert
        assertThat(result).isEmpty();
    }
}