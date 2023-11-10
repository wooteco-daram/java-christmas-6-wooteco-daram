package exception;

public class ChristmasException extends IllegalArgumentException {
    private static final String ERROR_PREFIX = "[ERROR]";
    ChristmasException(final String message) {
        super(message);
        System.out.println(ERROR_PREFIX + " " + message);
    }

    public static ChristmasException of(ErrorMessage errorMessage) {
        return new ChristmasException(errorMessage.getMessage());
    }
}
