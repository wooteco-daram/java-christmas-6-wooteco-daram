package controller;

import domain.reservation.ReservationDay;
import view.input.ReservationInputView;
import view.input.reader.Reader;
import view.output.ReservationOutputView;
import view.output.writer.Writer;

public class ReservationController extends Controller {
    private final ReservationOutputView reservationOutputView;
    private final ReservationInputView reservationInputView;

    public ReservationController(
            final Writer writer,
            final Reader reader
    ) {
        super(writer, reader);
        reservationOutputView = new ReservationOutputView(writer);
        reservationInputView = new ReservationInputView(reader);
    }

    public ReservationDay reserve() {
        reservationOutputView.printInputReservationDay();
        return reservationInputView.readReservationDay();
    }
}
