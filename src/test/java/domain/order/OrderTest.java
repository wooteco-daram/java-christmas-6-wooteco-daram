package domain.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
