package domain.benefit.discount;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.event.EventConstants;
import domain.menu.Menu;
import domain.order.Order;
import domain.order.OrderCount;
import domain.order.OrderGroup;
import domain.price.DiscountPrice;
import domain.reservation.ReservationDay;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("평일 할인 테스트")
class WeekDayDiscountTest {
    @Test
    @DisplayName("평일 할인 이벤트 이름을 조회합니다.")
    void Should_WEEKDAY_DISCOUNT_NAME_When_Get_Discount_Name() {
        //given
        final int day = 1;
        final ReservationDay reservationDay = new ReservationDay(day);
        final OrderGroup orderGroup = new OrderGroup(List.of(new Order(Menu.CHOCOLATE_CAKE, new OrderCount(1))));
        final String expected = "평일 할인";

        //when
        final WeekDayDiscount weekDayDiscount = new WeekDayDiscount(reservationDay, orderGroup);
        final String actual = weekDayDiscount.getDiscountName();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("평일일 때 할인한다.")
    @ParameterizedTest
    @CsvSource(value = {"3:2023", "4:2023", "5:2023", "6:2023", "7:2023", "8:0"}, delimiter = ':')
    void Should_Discount_When_Week_Days(final int day, final long expectedPrice) {
        //given
        final ReservationDay reservationDay = new ReservationDay(day);
        final OrderGroup orderGroup = new OrderGroup(List.of(new Order(Menu.CHOCOLATE_CAKE, new OrderCount(1))));
        final DiscountPrice expected = DiscountPrice.from(expectedPrice);

        //when
        final WeekDayDiscount weekDayDiscount = new WeekDayDiscount(reservationDay, orderGroup);
        final DiscountPrice actual = weekDayDiscount.getDiscountPrice();

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

    @DisplayName("평일일 때 디저트 메뉴를 메뉴 1개당 2,023원씩 할인한다.")
    @Test
    void Should_Discount_Multiply_OrderCount_When_Desserts_Prepared() {
        //given
        final ReservationDay reservationDay = new ReservationDay(3);
        final OrderGroup orderGroup = new OrderGroup(
                List.of(
                        new Order(Menu.CHOCOLATE_CAKE, new OrderCount(1)),
                        new Order(Menu.ICE_CREAM, new OrderCount(1))
                )
        );
        final DiscountPrice expected = DiscountPrice.from(2_023L * 2);

        //when
        final WeekDayDiscount weekDayDiscount = new WeekDayDiscount(reservationDay, orderGroup);
        final DiscountPrice actual = weekDayDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }
}
