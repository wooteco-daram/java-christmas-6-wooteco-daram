package domain.price;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("결제 금액 테스트")
class PaymentPriceTest {
    @Test
    @DisplayName("결제 금액은 총 금액에서 총 할인 금액을 뺍니다.")
    void Should_TotalPrice_Subtract_TotalDiscountPrice_When_Payment_Price_Calculate() {
        //given
        final TotalPrice totalPrice = new TotalPrice(
                List.of(Price.from(10_000L))
        );
        final TotalDiscountPrice totalDiscountPrice = new TotalDiscountPrice(
                List.of(DiscountPrice.from(5_000L))
        );

        final String expected = "5,000원";

        //when
        final PaymentPrice paymentPrice = new PaymentPrice(totalPrice, totalDiscountPrice);
        final String actual = paymentPrice.toString();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
