package christmas.domain.model.classes.expectedVisitDay;

public enum ExpectedVisitDayErrorMessage {
    /**
     * 방문할 날짜에 숫자가 입력되지 않은 경우
     * 1 이상 31 이하의 숫자를 입력하지 않은 경우
     */
    ERROR_INVALID_EXPECTED_DAY("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private final String message;

    ExpectedVisitDayErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
