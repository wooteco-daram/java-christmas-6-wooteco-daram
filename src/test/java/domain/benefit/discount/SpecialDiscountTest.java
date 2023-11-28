package domain.benefit.discount;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.price.DiscountPrice;
import domain.price.Price;
import domain.reservation.ReservationDay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("특별 할인 테스트")
class SpecialDiscountTest {
    @DisplayName("별이 적힌 날엔 1000원이 할인된다. 그 외엔 할인되지 않는다.")
    @ParameterizedTest
    @CsvSource(value = {"1:0", "3:1000", "10:1000", "17:1000", "24:1000", "25:1000", "31:1000"}, delimiter = ':')
    void Should_Discount_When_Special_Days(final int day, final long expectedPrice) {
        //given
        final ReservationDay reservationDay = new ReservationDay(day);
        final DiscountPrice expected = new DiscountPrice(new Price(expectedPrice));

        //when
        final SpecialDiscount specialDiscount = new SpecialDiscount(reservationDay);
        final DiscountPrice actual = specialDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }
}
