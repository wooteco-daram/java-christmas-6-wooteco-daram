package domain.price;

public class PaymentPrice {

    private final Price paymentPrice;

    public PaymentPrice(
            final TotalPrice totalPrice,
            final TotalDiscountPrice totalDiscountPrice
    ) {
        final Price totalPriceConverted = totalPrice.getTotalPrice();
        final Price totalDiscountPriceConverted = totalDiscountPrice.getTotalDiscountPrice();
        paymentPrice = totalPriceConverted.subtract(totalDiscountPriceConverted);
    }

    @Override
    public String toString() {
        return paymentPrice.toString();
    }
}
