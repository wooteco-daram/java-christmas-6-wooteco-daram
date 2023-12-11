package domain.benefit.discount;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.event.EventConstants;
import domain.price.DiscountPrice;
import domain.reservation.ReservationDay;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("특별 할인 테스트")
class SpecialDiscountTest {
    @Test
    @DisplayName("특별 할인 이벤트 이름을 출력합니다.")
    void Should_SPECIAL_DISCOUNT_NAME_When_Get_Discount_Name() {
        //given
        final ReservationDay reservationDay = new ReservationDay(3);
        final String expected = "특별 할인";

        //when
        final SpecialDiscount specialDiscount = new SpecialDiscount(reservationDay);
        final String actual = specialDiscount.getDiscountName();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("일요일에 특별 할인을 받는다.")
    @ParameterizedTest(name = "{index}. {0} 날짜")
    @MethodSource("provideSundays")
    void Should_Special_Discount_When_Sundays(final LocalDate localDate) {
        //given
        final int day = localDate.getDayOfMonth();
        final ReservationDay reservationDay = new ReservationDay(day);
        final DiscountPrice expected = DiscountPrice.from(1_000L);

        //when
        final SpecialDiscount specialDiscount = new SpecialDiscount(reservationDay);
        final DiscountPrice actual = specialDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }

    private static Stream<Arguments> provideSundays() {
        final LocalDate firstDateOfTheMonth = YearMonth.of(
                EventConstants.EVENT_YEAR,
                EventConstants.EVENT_MONTH
        ).atDay(1);

        return firstDateOfTheMonth
                .datesUntil(firstDateOfTheMonth.plusMonths(1))
                .filter(localDate -> localDate.getDayOfWeek() == DayOfWeek.SUNDAY)
                .map(Arguments::of);
    }

    @DisplayName("크리스마스 날에 특별 할인을 받는다.")
    @Test
    void Should_Special_Discount_When_ChristmasDay() {
        //given
        final ReservationDay reservationDay = new ReservationDay(EventConstants.CHRISTMAS_EVENT_DAY);
        final DiscountPrice expected = DiscountPrice.from(1_000L);

        //when
        final SpecialDiscount specialDiscount = new SpecialDiscount(reservationDay);
        final DiscountPrice actual = specialDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }

    @DisplayName("주말과 크리스마스가 아닌 날엔 특별 할인을 받지 않는다.")
    @ParameterizedTest(name = "{index}. {0} 날짜")
    @MethodSource("provideInvalidSpecialDays")
    void Should_Not_Special_Discount_When_Not_Sundays_And_Not_Christmas(final LocalDate localDate) {
        //given
        final int day = localDate.getDayOfMonth();
        final ReservationDay reservationDay = new ReservationDay(day);
        final DiscountPrice expected = DiscountPrice.from(0);

        //when
        final SpecialDiscount specialDiscount = new SpecialDiscount(reservationDay);
        final DiscountPrice actual = specialDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }

    private static Stream<Arguments> provideInvalidSpecialDays() {
        final LocalDate firstDateOfTheMonth = YearMonth.of(
                EventConstants.EVENT_YEAR,
                EventConstants.EVENT_MONTH
        ).atDay(1);

        return firstDateOfTheMonth
                .datesUntil(firstDateOfTheMonth.plusMonths(1))
                .filter(localDate -> localDate.getDayOfWeek() != DayOfWeek.SUNDAY &&
                        localDate.getDayOfMonth() != EventConstants.CHRISTMAS_EVENT_DAY)
                .map(Arguments::of);
    }
}
