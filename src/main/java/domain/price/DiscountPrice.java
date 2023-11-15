package domain.price;

public record DiscountPrice(Price price) {

    public static DiscountPrice empty() {
        return new DiscountPrice(Price.empty());
    }

    @Override
    public String toString() {
        return "-" + price.toString();
    }
}
