package domain.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import domain.benefit.discount.Discount;
import domain.benefit.discount.DiscountConstants;
import domain.price.DiscountPrice;
import domain.price.Price;
import domain.price.TotalDiscountPrice;
import domain.price.TotalPrice;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("혜택 모음 테스트")
class BenefitGroupTest {

    @Test
    @DisplayName("할인 목록이 없으면 없음을 출력합니다.")
    void Should_None_String_When_Empty_Discounts() {
        //given
        final List<Discount> emptyDiscounts = List.of();
        final TotalPrice totalPrice = new TotalPrice(List.of());
        final String expected = "없음";

        //when
        final BenefitGroup benefitGroup = new BenefitGroup(emptyDiscounts, totalPrice);
        final String actual = benefitGroup.toString();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("총 할인 금액을 구합니다.")
    void Should_Get_Total_Discount_Price() {
        //given
        final List<Discount> discounts = List.of(new TestDiscount("할인1"));
        final List<Price> prices = List.of(
                Price.from(50_000L),
                Price.from(100_000L)
        );
        final TotalPrice totalPrice = new TotalPrice(prices);
        final Price expected = Price.from(10_000L);

        //when
        final BenefitGroup benefitGroup = new BenefitGroup(discounts, totalPrice);
        final TotalDiscountPrice totalDiscountPrice = benefitGroup.getTotalDiscountPrice();
        final Price actual = totalDiscountPrice.getPrice();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("총 할인 금액을 구합니다.")
    void Should_Print_Discount_Names_When_toString() {
        //given
        final List<Discount> discounts = List.of(
                new TestDiscount("할인1", DiscountPrice.from(1_000L)),
                new TestDiscount("할인2", DiscountPrice.from(5_000L)),
                new TestDiscount("할인3", DiscountPrice.from(100L))
        );
        final List<Price> prices = List.of(
                Price.from(50_000L),
                Price.from(100_000L)
        );
        final TotalPrice totalPrice = new TotalPrice(prices);
        final String expected = "할인1: -1,000원\n할인2: -5,000원\n할인3: -100원";

        //when
        final BenefitGroup benefitGroup = new BenefitGroup(discounts, totalPrice);
        final String actual = benefitGroup.toString();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("총 금액이 할인받을 수 있는 최소 금액보다 적다면 할인을 받지 못한다.")
    void Should_Not_Discount_When_Total_Price_Less_Than_Minimum_Discount_Price() {
        //given
        final List<Discount> discounts = List.of(new TestDiscount("할인1"));
        final List<Price> prices = List.of(
                DiscountConstants.MINIMUM_TOTAL_PRICE_FOR_DISCOUNT.subtract(Price.from(1L))
        );
        final TotalPrice totalPrice = new TotalPrice(prices);
        final String expected = "없음";

        //when
        final BenefitGroup benefitGroup = new BenefitGroup(discounts, totalPrice);
        final String actual = benefitGroup.toString();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("할인 금액이 0원이라면 무시한다.")
    void Should_Ignore_Zero_Discount_When_toString() {
        //given
        final List<Discount> discounts = List.of(
                new TestDiscount("할인1", DiscountPrice.from(1_000L)),
                new TestDiscount("할인2", DiscountPrice.from(0L))
        );
        final List<Price> prices = List.of(
                Price.from(50_000L)
        );
        final TotalPrice totalPrice = new TotalPrice(prices);
        final String expected = "할인2: 0원";

        //when
        final BenefitGroup benefitGroup = new BenefitGroup(discounts, totalPrice);
        final String actual = benefitGroup.toString();

        //then
        assertThat(actual).doesNotContain(expected);
    }

    private static class TestDiscount extends Discount {

        private final String discountName;
        private final DiscountPrice discountPrice;

        private TestDiscount(final String discountName) {
            this.discountName = discountName;
            discountPrice = DiscountPrice.from(10_000L);
        }

        private TestDiscount(final String discountName, final DiscountPrice discountPrice) {
            this.discountName = discountName;
            this.discountPrice = discountPrice;
        }

        @Override
        public String getDiscountName() {
            return discountName;
        }

        @Override
        public DiscountPrice getDiscountPrice() {
            return discountPrice;
        }
    }
}
