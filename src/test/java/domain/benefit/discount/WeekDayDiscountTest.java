package domain.benefit.discount;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.event.EventConstants;
import domain.menu.Menu;
import domain.order.Order;
import domain.order.OrderCount;
import domain.order.OrderGroup;
import domain.price.DiscountPrice;
import domain.reservation.ReservationDay;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("평일 할인 테스트")
class WeekDayDiscountTest {
    @Test
    @DisplayName("평일 할인 이벤트 이름을 출력합니다.")
    void Should_WEEKDAY_DISCOUNT_NAME_When_Get_Discount_Name() {
        //given
        final int day = 1;
        final ReservationDay reservationDay = new ReservationDay(day);
        final OrderGroup orderGroup = new OrderGroup(List.of(new Order(Menu.CHOCOLATE_CAKE, OrderCount.from(1L))));
        final String expected = "평일 할인";

        //when
        final WeekDayDiscount weekDayDiscount = new WeekDayDiscount(reservationDay, orderGroup);
        final String actual = weekDayDiscount.getDiscountName();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("평일일 때 할인한다.")
    @ParameterizedTest(name = "{index}. {0} 일")
    @MethodSource("provideWeekdays")
    void Should_Discount_When_Week_Days(final LocalDate localDate) {
        //given
        final int day = localDate.getDayOfMonth();
        final ReservationDay reservationDay = new ReservationDay(day);
        final OrderGroup orderGroup = new OrderGroup(List.of(new Order(Menu.CHOCOLATE_CAKE, OrderCount.from(1L))));
        final DiscountPrice expected = DiscountPrice.from(2_023L);

        //when
        final WeekDayDiscount weekDayDiscount = new WeekDayDiscount(reservationDay, orderGroup);
        final DiscountPrice actual = weekDayDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }

    private static Stream<Arguments> provideWeekdays() {
        final LocalDate firstDateOfTheMonth = YearMonth.of(
                EventConstants.EVENT_YEAR,
                EventConstants.EVENT_MONTH
        ).atDay(1);

        return firstDateOfTheMonth
                .datesUntil(firstDateOfTheMonth.plusMonths(1))
                .filter(localDate -> EventConstants.WEEKDAYS.contains(localDate.getDayOfWeek()))
                .map(Arguments::of);
    }

    @DisplayName("평일이 아니면 할인하지 않는다.")
    @Test
    void Should_Not_Discount_When_Not_Week_Days() {
        //given
        final LocalDate weekend = DiscountDateUtil.getFirstWeekend();
        final int day = weekend.getDayOfMonth();
        final ReservationDay reservationDay = new ReservationDay(day);
        final OrderGroup orderGroup = new OrderGroup(List.of(new Order(Menu.CHOCOLATE_CAKE, OrderCount.from(1L))));
        final DiscountPrice expected = DiscountPrice.empty();

        //when
        final WeekDayDiscount weekDayDiscount = new WeekDayDiscount(reservationDay, orderGroup);
        final DiscountPrice actual = weekDayDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }

    @DisplayName("평일일 때 디저트 메뉴를 메뉴 1개당 2,023원씩 할인한다.")
    @Test
    void Should_Discount_Multiply_OrderCount_When_Desserts_Prepared() {
        //given
        final LocalDate weekday = DiscountDateUtil.getFirstWeekday();
        final int day = weekday.getDayOfMonth();
        final ReservationDay reservationDay = new ReservationDay(day);
        final List<Order> orders = List.of(
                new Order(Menu.CHOCOLATE_CAKE, OrderCount.from(1L)),
                new Order(Menu.ICE_CREAM, OrderCount.from(1L))
        );
        final OrderGroup orderGroup = new OrderGroup(orders);
        final DiscountPrice expected = DiscountPrice.from(2_023L * orders.size());

        //when
        final WeekDayDiscount weekDayDiscount = new WeekDayDiscount(reservationDay, orderGroup);
        final DiscountPrice actual = weekDayDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }
}
