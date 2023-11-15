package domain.price;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("할인 금액 테스트")
class DiscountPriceTest {
    @ParameterizedTest
    @CsvSource(value = {"0:-0원", "500:-500원", "10000:-10,000원", "10000000:-10,000,000원"}, delimiter = ':')
    @DisplayName("할인 금액을 표시할 때 맨 앞에 '-' 글자를 표시한다.")
    void Should_Minus_Prefix_When_toString(final long price, final String expected) {
        //given
        final Price newPrice = new Price(price);
        final DiscountPrice discountPrice = new DiscountPrice(newPrice);

        //when
        final String actual = discountPrice.toString();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
