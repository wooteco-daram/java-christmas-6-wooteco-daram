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

    public static Price from(long price) {
        if (price == 0) {
            return empty();
        }

        return new Price(price);
    }

    private void validateNegativePrice(final long price) {
        if (price < 0) {
            throw GlobalException.from(ErrorMessage.INVALID_PRICE);
        }
    }

    public Price add(final Price otherPrice) {
        return Price.from(price + otherPrice.price);
    }

    public Price subtract(final Price otherPrice) {
        return Price.from(price - otherPrice.price);
    }

    public Price multiply(final long count) {
        return Price.from(price * count);
    }

    public boolean isLessThanEqualTo(final Price otherPrice) {
        return price <= otherPrice.price;
    }

    public boolean isGreaterThanEqualTo(final Price otherPrice) {
        return price >= otherPrice.price;
    }

    public boolean isGreaterThan(final Price otherPrice) {
        return price >= otherPrice.price;
    }

    @Override
    public String toString() {
        return String.format("%,d원", price);
    }
}
