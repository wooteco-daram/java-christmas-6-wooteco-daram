package domain.benefit.discount;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.price.DiscountPrice;
import domain.price.Price;
import domain.reservation.ReservationDay;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChristmasDiscountTest {
    @Test
    void Should_Empty_Price_When_After_Christmas_Day() {
        //given
        final ReservationDay reservationDay = new ReservationDay(26);
        final DiscountPrice expected = DiscountPrice.empty();

        //when
        final ChristmasDiscount christmasDiscount = new ChristmasDiscount(reservationDay);
        final DiscountPrice actual = christmasDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:1000", "2:1100", "24:3300", "25:3400"}, delimiter = ':')
    void Should_Increment_Price_When_Before_Christmas_Day(final int day, final long expectedPrice) {
        //given
        final ReservationDay reservationDay = new ReservationDay(day);
        final DiscountPrice expected = new DiscountPrice(new Price(expectedPrice));

        //when
        final ChristmasDiscount christmasDiscount = new ChristmasDiscount(reservationDay);
        final DiscountPrice actual = christmasDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }
}
