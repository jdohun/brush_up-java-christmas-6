package christmas.domain.model.classes.orderInfo;

import christmas.domain.model.enums.menu.Menu;
import christmas.parser.InputOrderParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.*;

class OrderInfoTest {

    @DisplayName("정적 팩토리 생성 테스트")
    @Nested
    class createTest {

        @DisplayName("[Exception] 주문한 메뉴의 총 수량이 20을 초과하면 예외를 발생시킨다.")
        @Test
        void fromByOverTotalQuantity() {
            // arrange
            final EnumMap<Menu, Integer> sourceOverQuantity = InputOrderParser.parseOrderInfo("시저샐러드-1, 양송이수프-2, 바비큐립-3, 아이스크림-15");

            // act & assert
            assertThatThrownBy(() -> OrderInfo.from(sourceOverQuantity))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
        }

        @DisplayName("[Exception] 주문한 메뉴가 모두 음료 카테고리이면 예외를 발생시킨다.")
        @Test
        void fromByContainsOnlyBeverage() {
            // arrange
            final EnumMap<Menu, Integer> sourceOnlyBeverage = InputOrderParser.parseOrderInfo("제로콜라-1, 레드와인-2, 샴페인-3");

            // act & assert
            assertThatThrownBy(() -> OrderInfo.from(sourceOnlyBeverage))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.");
        }

        @DisplayName("[Success] 주문이 올바르게 되면 예외를 발생시키지 않는다.")
        @Test
        void fromByValid() {
            // arrange
            final EnumMap<Menu, Integer> validSource = InputOrderParser.parseOrderInfo("제로콜라-1, 레드와인-2, 티본스테이크-3");

            // act & assert
            assertThatCode(() -> OrderInfo.from(validSource))
                    .doesNotThrowAnyException();
        }
    }

    @DisplayName("기능 테스트")
    @Nested
    class MethodTest {
        @DisplayName("[calculate] 메뉴의 총 가격을 계산하여 반환한다.")
        @Test
        void calculateTotalAmount() {
            // arrange
            final EnumMap<Menu, Integer> validSource = InputOrderParser.parseOrderInfo("제로콜라-1, 아이스크림-2, 시저샐러드-3");
            OrderInfo orderInfo = OrderInfo.from(validSource);

            // act & assert
            assertThat(orderInfo.calculateTotalAmount())
                    .isEqualTo(37_000);
        }

        @DisplayName("[count] 메인 메뉴의 개수를 계산하여 반환한다.")
        @Test
        void countMainMenus() {
            // arrange
            final EnumMap<Menu, Integer> validSource = InputOrderParser.parseOrderInfo("제로콜라-1, 아이스크림-2, 해산물파스타-3, 크리스마스파스타-1");
            OrderInfo orderInfo = OrderInfo.from(validSource);

            // act & assert
            assertThat(orderInfo.countMainMenus())
                    .isEqualTo(4);
        }

        @DisplayName("[count] 디저트 메뉴의 개수를 계산하여 반환한다.")
        @Test
        void countDessertMenus() {
            // arrange
            final EnumMap<Menu, Integer> validSource = InputOrderParser.parseOrderInfo("제로콜라-1, 아이스크림-2, 초코케이크-3");
            OrderInfo orderInfo = OrderInfo.from(validSource);

            // act & assert
            assertThat(orderInfo.countDessertMenus())
                    .isEqualTo(5);
        }
    }
}