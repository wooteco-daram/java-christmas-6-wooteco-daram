package view.output;

import domain.order.OrderGroup;
import view.output.writer.Writer;

public class OrderOutputView extends OutputView {
    private static final String INPUT_MENU_MESSAGE =
            "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";

    public OrderOutputView(final Writer writer) {
        super(writer);
    }

    public void printInputMenuMessage() {
        writer.println(INPUT_MENU_MESSAGE);
    }

    public void printOrderMenus(final OrderGroup orderGroup) {
        writer.println(ORDER_MENU_MESSAGE);
        writer.println(orderGroup.toString());
    }
}
