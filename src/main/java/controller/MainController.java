package controller;

import domain.order.OrderGroup;
import domain.price.TotalDiscountPrice;
import domain.price.TotalPrice;
import domain.reservation.ReservationDay;
import view.input.reader.Reader;
import view.output.WelcomeOutputView;
import view.output.writer.Writer;

public class MainController extends Controller {
    private final WelcomeOutputView welcomeOutputView;

    private final ReservationController reservationController;
    private final OrderController orderController;
    private final PaymentController paymentController;
    private final BenefitController benefitController;
    private final EventBadgeController eventBadgeController;

    public MainController(
            final Writer writer,
            final Reader reader
    ) {
        super(writer, reader);

        welcomeOutputView = new WelcomeOutputView(writer);

        reservationController = new ReservationController(writer, reader);
        orderController = new OrderController(writer, reader);
        paymentController = new PaymentController(writer);
        benefitController = new BenefitController(writer);
        eventBadgeController = new EventBadgeController(writer);
    }

    public void run() {
        welcomeOutputView.printWelcomeMessage();
        final ReservationDay reservationDay = reservationController.reserve();
        final OrderGroup orderGroup = orderController.order(reservationDay);
        final TotalPrice totalPrice = paymentController.calculateTotalPriceBeforeDiscount(orderGroup);
        final TotalDiscountPrice totalDiscountPrice = benefitController.getTotalDiscountPrice(
                reservationDay,
                orderGroup,
                totalPrice
        );
        paymentController.calculatePaymentPriceAfterDiscount(
                totalPrice,
                totalDiscountPrice
        );
        eventBadgeController.calculateEventBadge(totalDiscountPrice);
    }
}
