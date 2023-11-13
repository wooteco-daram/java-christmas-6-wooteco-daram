package menu;

public record Price(long price) {

    public Price {
        validateNegativePrice(price);
    }

    private void validateNegativePrice(final long price) {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 음수가 될 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return String.format("%,d", price);
    }
}
