package view.output;

import domain.order.PresentOrderGroup;
import view.output.writer.Writer;

public class PresentOutputView extends OutputView {
    private static final String PRESENT_MENU_MESSAGE = "<증정 메뉴>";
    public PresentOutputView(final Writer writer) {
        super(writer);
    }

    public void printPresentMenu(final PresentOrderGroup presentOrder) {
        writer.println(PRESENT_MENU_MESSAGE);
        writer.println(presentOrder.toString());
    }
}
