package exception;


import util.exception.ExceptionHandler;

public class GlobalException extends IllegalArgumentException {
    public GlobalException(final String message) {
        super(message);
        ExceptionHandler.handle(this);
    }

    public static GlobalException from(final ErrorMessage errorMessage) {
        return new GlobalException(errorMessage.getMessage());
    }
}
