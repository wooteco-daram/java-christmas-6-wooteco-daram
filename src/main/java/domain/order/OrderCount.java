package domain.order;

import exception.ErrorMessage;
import exception.GlobalException;

public record OrderCount(long count) {
    private static final int MINIMUM_COUNT = 1;

    public OrderCount {
        validateCount(count);
    }

    private void validateCount(final long count) {
        if (count < MINIMUM_COUNT) {
            throw GlobalException.from(ErrorMessage.INVALID_MENU);
        }
    }

    public static OrderCount from(final String countForm) {
        final int count = OrderCountParser.parse(countForm);
        return new OrderCount(count);
    }

    private static class OrderCountParser {
        private static int parse(final String countForm) {
            try {
                return Integer.parseInt(countForm);
            } catch (NumberFormatException exception) {
                throw GlobalException.from(ErrorMessage.INVALID_MENU);
            }
        }
    }
}
