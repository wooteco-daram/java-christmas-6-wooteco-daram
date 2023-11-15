package domain.price;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("총 금액 테스트")
class TotalPriceTest {
    @Test
    @DisplayName("총 금액은 모든 가격을 더합니다.")
    void Should_Add_All_Prices_When_Prices_Given() {
        //given
        final List<Price> prices = List.of(
                new Price(1_000),
                new Price(10_000),
                new Price(20_000)
        );

        final Price expected = new Price(31_000);

        //when
        final TotalPrice totalPrice = new TotalPrice(prices);
        final Price actual = totalPrice.getTotalPrice();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("총 금액이 0원일 때 '없음'을 표시합니다.")
    void Should_None_String_When_All_Prices_Are_Zero() {
        //given
        final TotalPrice totalPrice = new TotalPrice(List.of(new Price(0)));
        final String expected = "없음";

        //when
        final String actual = totalPrice.toString();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("총 금액을 표시합니다.")
    void Should_Total_Price_String_When_toString() {
        //given
        final List<Price> prices = List.of(
                new Price(1_000),
                new Price(10_000),
                new Price(20_000)
        );

        final String expected = "31,000원";

        //when
        final TotalPrice totalPrice = new TotalPrice(prices);
        final String actual = totalPrice.toString();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
