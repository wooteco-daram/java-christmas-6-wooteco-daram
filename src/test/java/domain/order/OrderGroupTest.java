package domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주문 묶음 테스트")
class OrderGroupTest {
    @Test
    @DisplayName("주문이 중복된 경우 예외를 발생시킵니다.")
    void Should_Throw_Invalid_Menu_When_Order_Duplicates() {
        //given
        final List<Order> orders = List.of(
                Order.from("양송이수프-1"),
                Order.from("양송이수프-2")
        );

        //when,then
        assertThatThrownBy(() -> new OrderGroup(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_MENU.getMessage());
    }

    @Test
    @DisplayName("총 주문 수량이 넘어가면 예외를 발생시킵니다.")
    void Should_Throw_Invalid_Menu_When_Total_Order_Count_Over_Max() {
        //given
        final List<Order> orders = List.of(
                Order.from("양송이수프-10"),
                Order.from("티본스테이크-10"),
                Order.from("제로콜라-1")
        );

        //when,then
        assertThatThrownBy(() -> new OrderGroup(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_MENU.getMessage());
    }

    @Test
    @DisplayName("음료수만 주문하면 예외를 발생시킵니다.")
    void Should_Throw_Invalid_Menu_When_Only_Drinks() {
        //given
        final List<Order> orders = List.of(
                Order.from("제로콜라-1"),
                Order.from("레드와인-2")
        );

        //when,then
        assertThatThrownBy(() -> new OrderGroup(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_MENU.getMessage());
    }

    @Test
    @DisplayName("여러 주문 형식 문자열로 주문 묶음을 생성합니다.")
    void Should_Create_Order_Group_When_Order_Group_Form_Exist() {
        //given
        final String orderGroupForm = "양송이수프-1,제로콜라-2";

        final List<Order> expectedOrders = List.of(
                Order.from("양송이수프-1"),
                Order.from("제로콜라-2")
        );

        final OrderGroup expectedOrderGroup = new OrderGroup(expectedOrders);
        final String expected = expectedOrderGroup.toString();

        //when
        final OrderGroup actualOrderGroup = OrderGroup.from(orderGroupForm);
        final String actual = actualOrderGroup.toString();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
