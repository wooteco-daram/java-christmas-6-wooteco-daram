package view.output;

import domain.price.PaymentPrice;
import domain.price.TotalPrice;
import view.output.writer.Writer;

public class PaymentOutputView extends OutputView {
    private static final String TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE = "<할인 전 총주문 금액>";
    private static final String ESTIMATED_PAYMENT_PRICE_AFTER_DISCOUNT_MESSAGE = "<할인 후 예상 결제 금액>";

    public PaymentOutputView(final Writer writer) {
        super(writer);
    }

    public void printTotalPriceBeforeDiscount(final TotalPrice totalPrice) {
        writer.println(TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE);
        writer.println(totalPrice.toString());
    }

    public void printPaymentPriceAfterDiscount(final PaymentPrice paymentPrice) {
        writer.println(ESTIMATED_PAYMENT_PRICE_AFTER_DISCOUNT_MESSAGE);
        writer.println(paymentPrice.toString());
    }
}
