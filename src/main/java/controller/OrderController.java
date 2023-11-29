package controller;

import domain.order.OrderGroup;
import domain.reservation.ReservationDay;
import view.input.OrderInputView;
import view.input.reader.Reader;
import view.output.EventOutputView;
import view.output.OrderOutputView;
import view.output.writer.Writer;

public class OrderController extends Controller {
    private final OrderOutputView orderOutputView;
    private final EventOutputView eventOutputView;
    private final OrderInputView orderInputView;

    public OrderController(
            Writer writer,
            Reader reader
    ) {
        super(writer, reader);
        orderOutputView = new OrderOutputView(writer);
        eventOutputView = new EventOutputView(writer);
        orderInputView = new OrderInputView(reader);
    }

    public OrderGroup order(final ReservationDay reservationDay) {
        orderOutputView.printInputMenuMessage();
        final OrderGroup orderGroup = orderInputView.readOrder();

        eventOutputView.printEventPreviewMessage(reservationDay);
        eventOutputView.printNewLine();

        orderOutputView.printOrderMenus(orderGroup);
        orderOutputView.printNewLine();

        return orderGroup;
    }
}
