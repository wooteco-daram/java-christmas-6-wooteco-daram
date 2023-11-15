package view.input;

import domain.order.OrderGroup;
import exception.GlobalException;
import view.input.reader.Reader;

public class OrderInputView extends InputView {

    public OrderInputView(final Reader reader) {
        super(reader);
    }

    public OrderGroup readOrder() {
        final String orderForm = reader.read();
        try {
            return OrderGroup.from(orderForm);
        } catch (GlobalException exception) {
            return readOrder();
        }
    }
}
