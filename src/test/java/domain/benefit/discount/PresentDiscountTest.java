package domain.benefit.discount;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.order.PresentOrderGroup;
import domain.price.DiscountPrice;
import domain.price.Price;
import domain.price.TotalPrice;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("증정 할인 테스트")
class PresentDiscountTest {
    @Test
    @DisplayName("증정 이벤트 이름을 조회합니다.")
    void Should_PRESENT_DISCOUNT_NAME_When_Get_Discount_Name() {
        //given
        final TotalPrice totalPrice = new TotalPrice(List.of(
                Price.from(120_000)
        ));
        final PresentOrderGroup presentOrderGroup = new PresentOrderGroup(totalPrice);
        final String expected = "증정 이벤트";

        //when
        final PresentDiscount presentDiscount = new PresentDiscount(presentOrderGroup);
        final String actual = presentDiscount.getDiscountName();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "총 금액 {0}원 => {1}원 할인")
    @CsvSource(value = {"100_000:0", "120_000:25_000"}, delimiter = ':')
    @DisplayName("증정 품목이 있을 시 증정 할인을 적용합니다.")
    void Should_Discount_When_Present_Order_Exist(final long price, final long expectedPrice) {
        //given
        final TotalPrice totalPrice = new TotalPrice(List.of(
                Price.from(price)
        ));
        final PresentOrderGroup presentOrderGroup = new PresentOrderGroup(totalPrice);
        final DiscountPrice expected = DiscountPrice.from(expectedPrice);

        //when
        final PresentDiscount presentDiscount = new PresentDiscount(presentOrderGroup);
        final DiscountPrice actual = presentDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }
}
