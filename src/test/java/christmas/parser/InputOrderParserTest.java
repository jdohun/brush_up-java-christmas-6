package christmas.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputOrderParserTest {

    @DisplayName("[Exception] 처음 시작이 0회 이상의 공백 이후 쉼표 | 마지막이 쉼표 이후 0회 이상 공백 | 쉼표 사이에 0회 이상의 공백만 존재하는 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {" , 메뉴-1", "메뉴-1, ", "메뉴-1, ,메뉴-1"})
    void parseOrderInfoByInvalidOrderFormat(String invalidOrder) {
        assertThatThrownBy(() -> InputOrderParser.parseOrderInfo(invalidOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("[Exception] 주문 아이템의 형식이 '한글-1 이상의 정수' 가 아닌 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"menu-1", "메뉴-0", "메뉴:1"})
    void parseOrderInfoByInvalidItemFormat(String invalidOrder) {
        assertThatThrownBy(() -> InputOrderParser.parseOrderInfo(invalidOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("[Exception] '한글-1 이상의 정수'의 주문 형식을 지키더라도 메뉴가 존재하지 않으면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"한글-1", "메뉴-2", "음식-123"})
    void parseOrderInfoByInvalidMenu(String invalidMenu) {
        assertThatThrownBy(() -> InputOrderParser.parseOrderInfo(invalidMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("[Exception] '존재하는 메뉴-1 이상의 정수'의 주문 형식을 지키더라도 메뉴가 중복되면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-1, 타파스-2, 양송이수프-2"})
    void parseOrderInfoByDuplicatedMenu(String duplicatedMenu) {
        assertThatThrownBy(() -> InputOrderParser.parseOrderInfo(duplicatedMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("[Success] '존재하는 메뉴-1 이상의 정수' 를 지키면서 중복되는 메뉴가 존재하지 않으면 예외를 발생시키지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-1", "타파스-2", "시저샐러드-123"})
    void parseOrderInfoByValid(String validValue) {
        assertThatCode(() -> InputOrderParser.parseOrderInfo(validValue))
                .doesNotThrowAnyException();
    }
}