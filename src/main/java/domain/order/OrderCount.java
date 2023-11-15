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
        try {
            final int count = Integer.parseInt(countForm);
            return new OrderCount(count);
        } catch (NumberFormatException exception) {
            throw GlobalException.from(ErrorMessage.INVALID_MENU);
        }
    }
}
