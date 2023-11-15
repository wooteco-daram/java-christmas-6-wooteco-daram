package view.output;

import domain.reservation.ReservationDay;
import view.output.writer.Writer;

public class EventOutputView extends OutputView {
    private static final String EVENT_PREVIEW_MESSAGE_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    public EventOutputView(Writer writer) {
        super(writer);
    }

    public void printEventPreviewMessage(final ReservationDay reservationDay) {
        writer.println(EVENT_PREVIEW_MESSAGE_FORMAT.formatted(reservationDay.reservationDay()));
    }

}
