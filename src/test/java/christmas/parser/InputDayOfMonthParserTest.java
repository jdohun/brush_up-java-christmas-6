package christmas.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputDayOfMonthParserTest {

    @DisplayName("[Exception] 한 자리 또는 두 자리 양의 정수가 아니면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "100", " ", " 12", " 123", "1 ", "-1", "-12"})
    void parseDayOfMonthByInvalid(String invalidValue) {
        assertThatThrownBy(() -> InputDayOfMonthParser.parseDayOfMonth(invalidValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("[Success] 한 자리 또는 두 자리 양의 정수는 예외를 발생시키지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "99"})
    void parseDayOfMonthByValid(String validValue) {
        assertThatCode(() -> InputDayOfMonthParser.parseDayOfMonth(validValue))
                .doesNotThrowAnyException();
    }
}