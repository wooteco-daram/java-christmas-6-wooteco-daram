package view.input;

import domain.reservation.ReservationDay;
import exception.ErrorMessage;
import exception.GlobalException;
import util.DateUtil;
import util.exception.ExceptionHandler;
import view.input.reader.Reader;

public class ReservationInputView extends InputView {
    public ReservationInputView(final Reader reader) {
        super(reader);
    }

    public ReservationDay readReservationDay() {
        final String reservationDayString = reader.read();
        try {
            final int reservationDay = Integer.parseInt(reservationDayString);
            return new ReservationDay(reservationDay);
        } catch (NumberFormatException exception) {
            ExceptionHandler.handle(GlobalException.from(ErrorMessage.INVALID_DATE));
            return readReservationDay();
        } catch (GlobalException exception) {
            ExceptionHandler.handle(exception);
            return readReservationDay();
        }
    }
}
