package domain.order;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.menu.Menu;
import domain.price.Price;
import exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주문 테스트")
class OrderTest {
    @Test
    @DisplayName("메뉴와 수량을 문자열로 받아 생성합니다.")
    void Should_Create_Order_When_Order_Form_Prepared() {
        //given
        final String expected = "양송이수프 1개";

        //when
        final Order order = Order.from("양송이수프-1");
        final String actual = order.toString();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("주문 양식이 올바르지 않다면 예외를 발생시킵니다.")
    void When_Throw_Invalid_Menu_Exception_When_Invalid_Order_Form() {
        //given
        final String orderForm = "invalid";

        //when, then
        assertThatThrownBy(() -> Order.from(orderForm))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_MENU.getMessage());
    }

    @Test
    @DisplayName("메뉴를 찾을 수 없다면 예외를 발생시킵니다.")
    void When_Throw_Invalid_Menu_Exception_When_Menu_Not_Found() {
        //given
        final String orderForm = "NONMENU-1";

        //when, then
        assertThatThrownBy(() -> Order.from(orderForm))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_MENU.getMessage());
    }

    @Test
    @DisplayName("수량이 올바르지 않다면 예외를 발생시킵니다.")
    void When_Throw_Invalid_Menu_Exception_When_Order_Count_Format_Invalid() {
        //given
        final String orderForm = "양송이수프-a";

        //when, then
        assertThatThrownBy(() -> Order.from(orderForm))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_MENU.getMessage());
    }

    @Test
    @DisplayName("주문 가격은 메뉴의 가격에 수량 수를 곱합니다.")
    void Should_Menu_Price_Multiply_Order_Count_When_Get_Price() {
        //given
        final int orderCount = 5;
        final Price expected = Menu.PINE_MUSHROOM_SOUP.getPrice().multiply(orderCount);

        //when
        final String orderForm = Menu.PINE_MUSHROOM_SOUP.getName() + "-" + orderCount;
        final Order order = Order.from(orderForm);
        final Price actual = order.getPrice();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
