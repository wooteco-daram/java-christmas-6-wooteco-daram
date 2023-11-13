package util.exception;

public class ExceptionHandler {
    private static final String ERROR_PREFIX = "[ERROR]";

    private ExceptionHandler() {
        // No instances
    }

    public static void handle(final Exception exception) {
        printExceptionMessage(exception.getMessage());
    }

    private static void printExceptionMessage(final String exceptionMessage) {
        System.out.println(ERROR_PREFIX + " " + exceptionMessage);
    }
}
