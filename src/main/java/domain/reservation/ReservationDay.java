package domain.reservation;

import domain.event.EventConstants;
import exception.ErrorMessage;
import exception.GlobalException;
import util.DateUtil;

public record ReservationDay(int reservationDay) {

    public ReservationDay {
        validateReservationDay(reservationDay);
    }

    private void validateReservationDay(final int reservationDay) {
        if (!DateUtil.isValidDate(EventConstants.EVENT_YEAR, EventConstants.EVENT_MONTH, reservationDay)) {
            throw GlobalException.from(ErrorMessage.INVALID_DATE);
        }
    }

    public boolean afterFrom(final int day) {
        return reservationDay > day;
    }
}
