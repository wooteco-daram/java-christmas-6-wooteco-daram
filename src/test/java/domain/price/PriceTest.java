package domain.price;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.price.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("가격 테스트")
class PriceTest {
    @Test
    @DisplayName("가격이 음수이면 IllegalArgumentException 예외를 발생한다.")
    void Should_IllegalArgumentExceptionThrown_When_Price_Is_Negative() {
        //when, then
        assertThatThrownBy(() -> Price.from(-1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("가격은 음수가 될 수 없습니다.");
    }

    @Test
    @DisplayName("0원인 가격을 반환한다.")
    void Should_Zero_Price_When_Empty() {
        //given
        final Price expected = Price.empty();

        //when
        final Price actual = Price.empty();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("두 개의 가격을 더한다.")
    void Should_Add_Price_When_Two_Price_Add() {
        //given
        final Price oneHundredPrice = Price.from(100L);
        final Price twoHundredPrice = Price.from(200L);

        final Price expected = Price.from(300L);

        //when
        final Price actual = oneHundredPrice.add(twoHundredPrice);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("두 개의 가격을 뺀다.")
    void Should_Subtract_Price_When_Two_Price_Subtract() {
        //given
        final Price oneThousandPrice = Price.from(1_000L);
        final Price fiveHundredPrice = Price.from(500L);

        final Price expected = Price.from(500L);

        //when
        final Price actual = oneThousandPrice.subtract(fiveHundredPrice);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("가격을 수량만큼 곱한다.")
    void Should_Multiply_Price_When_Count_Is_Given() {
        //given
        final Price oneThousandPrice = Price.from(1_000L);
        final long count = 3;

        final Price expected = Price.from(3_000L);

        //when
        final Price actual = oneThousandPrice.multiply(count);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:0원", "500:500원", "10000:10,000원", "10000000:10,000,000원"}, delimiter = ':')
    @DisplayName("가격 표시할 때 세 자릿수 단위로 콤마를 찍고 뒤에 원을 붙인다.")
    void Should_Three_Digit_Comma_And_Won_Postfix_When_toString(final long price, final String expected) {
        //given
        final Price newPrice = Price.from(price);

        //when
        final String actual = newPrice.toString();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
