package domain.price;

import exception.ErrorMessage;
import exception.GlobalException;

public record Price(long price) {
    private static final Price EMPTY_PRICE = new Price(0);

    public Price {
        validateNegativePrice(price);
    }

    public static Price empty() {
        return EMPTY_PRICE;
    }

    private void validateNegativePrice(final long price) {
        if (price < 0) {
            throw GlobalException.from(ErrorMessage.INVALID_PRICE);
        }
    }

    public Price add(final Price otherPrice) {
        return new Price(price + otherPrice.price);
    }

    public Price subtract(final Price otherPrice) {
        return new Price(price - otherPrice.price);
    }

    public Price multiply(final long count) {
        return new Price(price * count);
    }

    public boolean isLessThan(final Price otherPrice) {
        return price < otherPrice.price;
    }

    @Override
    public String toString() {
        return String.format("%,d원", price);
    }
}
