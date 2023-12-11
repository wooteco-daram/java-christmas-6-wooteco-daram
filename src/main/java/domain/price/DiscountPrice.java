package domain.price;

public record DiscountPrice(Price price) {
    private static final DiscountPrice EMPTY_DISCOUNT_PRICE = new DiscountPrice(Price.empty());

    public static DiscountPrice from(long discountPrice) {
        if (discountPrice == 0) {
            return DiscountPrice.empty();
        }

        return new DiscountPrice(
                Price.from(discountPrice)
        );
    }

    public static DiscountPrice empty() {
        return EMPTY_DISCOUNT_PRICE;
    }

    public DiscountPrice add(final DiscountPrice otherDiscountPrice) {
        final Price addedPrice = price.add(otherDiscountPrice.price);
        return new DiscountPrice(addedPrice);
    }

    public boolean isLessThanEqualTo(final DiscountPrice otherDiscountPrice) {
        return price.isLessThanEqualTo(otherDiscountPrice.price());
    }

    public boolean isGreaterThan(final DiscountPrice otherDiscountPrice) {
        return price.isGreaterThan(otherDiscountPrice.price());
    }

    @Override
    public String toString() {
        return "-" + price;
    }
}
