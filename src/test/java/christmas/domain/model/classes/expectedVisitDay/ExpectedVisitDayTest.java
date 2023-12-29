package christmas.domain.model.classes.expectedVisitDay;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExpectedVisitDayTest {

    @DisplayName("[Exception] 정해진 범위(1 ~ 31)를 벗어나는 날짜는 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    void fromByInvalid(int invalid) {
        assertThatThrownBy(() -> ExpectedVisitDay.from(invalid))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("[Success] 정해진 범위(1 ~ 31)를 만족하는 날짜는 예외를 발생시키지 않는다.")
    @ParameterizedTest(name = "경계값 테스트 : {0}")
    @ValueSource(ints = {1, 2, 30, 31, 15})
    void fromByValid(int valid) {
        assertThatCode(() -> ExpectedVisitDay.from(valid))
                .doesNotThrowAnyException();
    }
}