package domain.price;

public record Price(long price) {
    private static final long EMPTY_PRICE = 0L;

    public Price {
        validateNegativePrice(price);
    }

    public static Price empty() {
        return new Price(EMPTY_PRICE);
    }

    private void validateNegativePrice(final long price) {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 음수가 될 수 없습니다.");
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
