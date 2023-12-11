package domain.benefit.discount;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.price.DiscountPrice;
import domain.price.Price;
import domain.reservation.ReservationDay;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("크리스마스 디데이 할인 테스트")
class ChristmasDiscountTest {
    @Test
    @DisplayName("크리스마스 디데이 할인 이름을 출력합니다.")
    void Should_CHRISTMAS_DISCOUNT_NAME_When_Get_Discount_Name() {
        //given
        final ReservationDay reservationDay = new ReservationDay(1);
        final String expected = "크리스마스 디데이 할인";

        //when
        final ChristmasDiscount christmasDiscount = new ChristmasDiscount(reservationDay);
        final String actual = christmasDiscount.getDiscountName();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("크리스마스 디데이 다음 날 부터는 0원을 출력합니다.")
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
    @DisplayName("크리스마스 디데이가 다가올수록 할인 가격을 올립니다.")
    void Should_Increment_Price_When_Before_Christmas_Day(final int day, final long expectedPrice) {
        //given
        final ReservationDay reservationDay = new ReservationDay(day);
        final DiscountPrice expected = DiscountPrice.from(expectedPrice);

        //when
        final ChristmasDiscount christmasDiscount = new ChristmasDiscount(reservationDay);
        final DiscountPrice actual = christmasDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }
}
