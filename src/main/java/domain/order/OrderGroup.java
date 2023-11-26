package domain.order;

import controller.OrderController;
import domain.menu.Menu;
import domain.menu.MenuBoard;
import domain.price.Price;
import domain.price.TotalPrice;
import exception.ErrorMessage;
import exception.GlobalException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record OrderGroup(List<Order> orders) {
    private static final int MAX_ORDER_SIZE = 20;

    public OrderGroup {
        validateDuplicate(orders);
        validateOrderSize(orders);
        validateOnlyDrink(orders);
    }

    private void validateDuplicate(final List<Order> orders) {
        final Set<Order> uniqueOrders = new HashSet<>(orders);
        if (uniqueOrders.size() != orders.size()) {
            throw GlobalException.from(ErrorMessage.INVALID_MENU);
        }
    }

    private void validateOrderSize(final List<Order> orders) {
        final long orderCount = getOrderCount(orders);
        if (orderCount > MAX_ORDER_SIZE) {
            throw GlobalException.from(ErrorMessage.INVALID_MENU);
        }
    }

    private long getOrderCount(final List<Order> orders) {
        return orders.stream()
                .map(Order::getCount)
                .map(OrderCount::count)
                .reduce(Long::sum)
                .orElseThrow(() -> GlobalException.from(ErrorMessage.INVALID_MENU));
    }

    private void validateOnlyDrink(final List<Order> orders) {
        final long drinkCount = getDrinkCount(orders);
        if (drinkCount == orders.size()) {
            throw GlobalException.from(ErrorMessage.INVALID_MENU);
        }
    }

    private long getDrinkCount(final List<Order> orders) {
        return orders.stream()
                .filter(order -> MenuBoard.DRINK.hasMenu(order.getMenu()))
                .count();
    }

    public static OrderGroup from(final String orderForm) {
        final List<Order> orders = OrderParser.parse(orderForm);
        return new OrderGroup(orders);
    }

    public TotalPrice getTotalPrice() {
        final List<Price> prices = orders.stream()
                .map(Order::getPrice)
                .toList();
        return new TotalPrice(prices);
    }

    public List<Menu> getMenus() {
        return orders.stream()
                .map(Order::getMenu)
                .toList();
    }

    @Override
    public String toString() {
        return orders.stream()
                .map(Order::toString)
                .collect(Collectors.joining("\n"));
    }

    private static class OrderParser {
        private static final String ORDER_DELIMITER = ",";

        public static List<Order> parse(final String orderForm) {
            final String[] orderFormSplit = orderForm.split(ORDER_DELIMITER);
            return Arrays.stream(orderFormSplit)
                    .map(String::trim)
                    .map(Order::from)
                    .toList();
        }
    }
}
