package domain.price;

public record DiscountPrice(Price price) {

    public static DiscountPrice from(long price) {
        return new DiscountPrice(new Price(price));
    }

    public static DiscountPrice empty() {
        return new DiscountPrice(Price.empty());
    }

    @Override
    public String toString() {
        return "-" + price;
    }
}
