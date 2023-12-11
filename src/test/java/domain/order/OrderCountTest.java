package domain.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주문 수량 테스트")
class OrderCountTest {
    @Test
    @DisplayName("최소 수량 개수보다 적으면 예외를 발생시킵니다.")
    void Should_Throw_When_Order_Count_Less_Than_Minimum_Count() {
        //given
        final long count = 0;

        //when, then
        assertThatThrownBy(() -> OrderCount.from(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("다른 주문 수량을 더합니다.")
    void Should_Added_Count_When_Add_To_Other_OrderCount() {
        //given
        final OrderCount orderCount = OrderCount.from(10L);

        final OrderCount expected = OrderCount.from(15L);

        //when
        final OrderCount newOrderCount = OrderCount.from(5L);
        final OrderCount actual = orderCount.add(newOrderCount);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("주문 수량 양식이 올바르지 않다면 예외를 발생합니다.")
    void Should_Throw_NumberFormatException_When_Invalid_Format() {
        //given
        final String countForm = "a";

        //when, then
        assertThatThrownBy(() -> OrderCount.from(countForm))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_MENU.getMessage());
    }
}
