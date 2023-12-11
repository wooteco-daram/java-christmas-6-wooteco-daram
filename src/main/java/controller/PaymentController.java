package controller;

import domain.order.OrderGroup;
import domain.price.PaymentPrice;
import domain.price.TotalDiscountPrice;
import domain.price.TotalPrice;
import view.output.PaymentOutputView;
import view.output.writer.Writer;

public class PaymentController extends Controller {
    private final PaymentOutputView paymentOutputView;


    public PaymentController(final Writer writer) {
        super(writer, null);
        paymentOutputView = new PaymentOutputView(writer);
    }

    public TotalPrice calculateTotalPriceBeforeDiscount(final OrderGroup orderGroup) {
        final TotalPrice totalPrice = orderGroup.getTotalPrice();
        paymentOutputView.printTotalPriceBeforeDiscount(totalPrice);
        paymentOutputView.printNewLine();
        return totalPrice;
    }

    public void calculatePaymentPriceAfterDiscount(
            final TotalPrice totalPrice,
            final TotalDiscountPrice totalDiscountPrice
    ) {
        final PaymentPrice paymentPrice = new PaymentPrice(totalPrice, totalDiscountPrice);
        paymentOutputView.printPaymentPriceAfterDiscount(paymentPrice);
        paymentOutputView.printNewLine();
    }
}
