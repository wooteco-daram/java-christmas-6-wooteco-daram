package domain.benefit.discount;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

@DisplayName("주말 할인 테스트")
class WeekEndDiscountTest {
    @Test
    @DisplayName("주말 할인 이벤트 이름을 출력합니다.")
    void Should_WEEKEND_DISCOUNT_NAME_When_Get_Discount_Name() {
        //given
        final int day = 1;
        final ReservationDay reservationDay = new ReservationDay(day);
        final OrderGroup orderGroup = new OrderGroup(List.of(new Order(Menu.CHOCOLATE_CAKE, new OrderCount(1))));
        final String expected = "주말 할인";

        //when
        final WeekEndDiscount weekEndDiscount = new WeekEndDiscount(reservationDay, orderGroup);
        final String actual = weekEndDiscount.getDiscountName();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("주말일 때 할인한다.")
    @ParameterizedTest(name = "{index}. {0} 일")
    @MethodSource("provideWeekends")
    void Should_Discount_When_Week_Ends(final LocalDate localDate) {
        //given
        final int day = localDate.getDayOfMonth();
        final ReservationDay reservationDay = new ReservationDay(day);
        final OrderGroup orderGroup = new OrderGroup(List.of(new Order(Menu.T_BONE_STEAK, new OrderCount(1))));
        final DiscountPrice expected = DiscountPrice.from(2_023L);

        //when
        final WeekEndDiscount weekEndDiscount = new WeekEndDiscount(reservationDay, orderGroup);
        final DiscountPrice actual = weekEndDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }

    private static Stream<Arguments> provideWeekends() {
        final LocalDate firstDateOfTheMonth = YearMonth.of(
                EventConstants.EVENT_YEAR,
                EventConstants.EVENT_MONTH
        ).atDay(1);

        return firstDateOfTheMonth
                .datesUntil(firstDateOfTheMonth.plusMonths(1))
                .filter(localDate -> EventConstants.WEEKENDS.contains(localDate.getDayOfWeek()))
                .map(Arguments::of);
    }

    @DisplayName("주말이 아니면 할인하지 않는다.")
    @Test
    void Should_Not_Discount_When_Not_Week_End() {
        //given
        final LocalDate weekday = DiscountDateUtil.getFirstWeekday();
        final int day = weekday.getDayOfMonth();
        final ReservationDay reservationDay = new ReservationDay(day);
        final OrderGroup orderGroup = new OrderGroup(List.of(new Order(Menu.T_BONE_STEAK, new OrderCount(1))));
        final DiscountPrice expected = DiscountPrice.empty();

        //when
        final WeekEndDiscount weekEndDiscount = new WeekEndDiscount(reservationDay, orderGroup);
        final DiscountPrice actual = weekEndDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }

    @DisplayName("주말일 때 메인 메뉴를 메뉴 1개당 2,023원씩 할인한다.")
    @Test
    void Should_Discount_Multiply_OrderCount_When_Main_Prepared() {
        //given
        final LocalDate weekday = DiscountDateUtil.getFirstWeekend();
        final int day = weekday.getDayOfMonth();
        final ReservationDay reservationDay = new ReservationDay(day);
        final List<Order> orders = List.of(
                new Order(Menu.T_BONE_STEAK, new OrderCount(1)),
                new Order(Menu.BBQ_RIBS, new OrderCount(1))
        );
        final OrderGroup orderGroup = new OrderGroup(orders);
        final DiscountPrice expected = DiscountPrice.from(2_023L * orders.size());

        //when
        final WeekEndDiscount weekEndDiscount = new WeekEndDiscount(reservationDay, orderGroup);
        final DiscountPrice actual = weekEndDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }
}
