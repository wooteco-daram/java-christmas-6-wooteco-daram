package domain.price;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("총 할인 금액 테스트")
class TotalDiscountPriceTest {
    @Test
    @DisplayName("총 할인 금액은 모든 할인 금액을 더합니다.")
    void Should_Add_All_Discount_Prices_When_Discount_Prices_Given() {
        //given
        final List<DiscountPrice> discountPrices = List.of(
                new DiscountPrice(new Price(1_000)),
                new DiscountPrice(new Price(10_000)),
                new DiscountPrice(new Price(20_000))
        );

        final Price expected = new Price(31_000);

        //when
        final TotalDiscountPrice totalDiscountPrice = new TotalDiscountPrice(discountPrices);
        final Price actual = totalDiscountPrice.getPrice();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("총 할인 금액이 0원일 때 '없음'을 표시합니다.")
    void Should_None_String_When_All_Discount_Prices_Are_Zero() {
        //given
        final TotalDiscountPrice totalDiscountPrice = new TotalDiscountPrice(
                List.of(
                        new DiscountPrice(new Price(0))
                )
        );
        final String expected = "없음";

        //when
        final String actual = totalDiscountPrice.toString();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("총 할인 금액을 표시합니다.")
    void Should_Total_Discount_Price_String_When_toString() {
        //given
        final List<DiscountPrice> discountPrices = List.of(
                new DiscountPrice(new Price(1_000)),
                new DiscountPrice(new Price(10_000)),
                new DiscountPrice(new Price(20_000))
        );

        final String expected = "-31,000원";

        //when
        final TotalDiscountPrice totalDiscountPrice = new TotalDiscountPrice(discountPrices);
        final String actual = totalDiscountPrice.toString();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
