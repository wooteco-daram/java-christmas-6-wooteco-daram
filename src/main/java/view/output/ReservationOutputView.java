package view.output;

import view.output.writer.Writer;

public class ReservationOutputView extends OutputView {

    private static final String INPUT_RESERVATION_DAY_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public ReservationOutputView(final Writer writer) {
        super(writer);
    }

    public void printInputReservationDay() {
        writer.println(INPUT_RESERVATION_DAY_MESSAGE);
    }
}
