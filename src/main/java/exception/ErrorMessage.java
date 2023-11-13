package exception;

public enum ErrorMessage {
    INVALID_MENU("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
