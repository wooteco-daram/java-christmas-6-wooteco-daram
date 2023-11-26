package domain.order;

import domain.menu.Menu;
import domain.price.Price;
import exception.ErrorMessage;
import exception.GlobalException;

public class Order {
    private final Menu menu;
    private final OrderCount count;

    public Order(
            final Menu menu,
            final OrderCount count
    ) {
        this.menu = menu;
        this.count = count;
    }

    public static Order from(final String order) {
        final OrderForm orderForm = OrderParser.parse(order);
        return new Order(
                orderForm.menu(),
                orderForm.orderCount()
        );
    }

    public Menu getMenu() {
        return menu;
    }

    public Price getPrice() {
        return menu.getPrice().multiply(count.count());
    }

    public OrderCount getCount() {
        return count;
    }

    @Override
    public String toString() {
        return menu.getName() + " " + count.count() + "개";
    }

    private record OrderForm(Menu menu, OrderCount orderCount) {
        private OrderForm(
                final String menu,
                final String orderCount
        ) {
            this(
                    Menu.findByName(menu),
                    OrderCount.from(orderCount)
            );
        }
    }

    private static class OrderParser {
        private static final String ORDER_SPLIT_DELIMITER = "-";
        private static final int ORDER_SPLIT_LENGTH = 2;

        public static OrderForm parse(final String orderForm) {
            final String[] orderSplit = orderForm.split(ORDER_SPLIT_DELIMITER);
            validateOrderSplit(orderSplit);

            return new OrderForm(
                    orderSplit[0],
                    orderSplit[1]
            );
        }

        private static void validateOrderSplit(final String[] orderSplit) {
            if (orderSplit.length != ORDER_SPLIT_LENGTH) {
                throw GlobalException.from(ErrorMessage.INVALID_MENU);
            }
        }
    }
}
