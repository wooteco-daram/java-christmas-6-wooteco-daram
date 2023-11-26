package domain.price;

public class PaymentPrice {

    private final Price price;

    public PaymentPrice(
            final TotalPrice totalPrice,
            final TotalDiscountPrice totalDiscountPrice
    ) {
        final Price totalPriceConverted = totalPrice.getPrice();
        final Price totalDiscountPriceConverted = totalDiscountPrice.getPrice();
        price = totalPriceConverted.subtract(totalDiscountPriceConverted);
    }

    @Override
    public String toString() {
        return price.toString();
    }
}
