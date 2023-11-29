package domain.benefit.discount;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import domain.menu.Menu;
import domain.order.Order;
import domain.order.OrderCount;
import domain.order.OrderGroup;
import domain.price.DiscountPrice;
import domain.price.Price;
import domain.reservation.ReservationDay;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("평일 할인 테스트")
class WeekDayDiscountTest {
    @DisplayName("평일일 때 할인한다.")
    @ParameterizedTest
    @CsvSource(value = {"3:2023", "4:2023", "5:2023", "6:2023", "7:2023", "8:0"}, delimiter = ':')
    void Should_Discount_When_Week_Days(final int day, final long expectedPrice) {
        //given
        final ReservationDay reservationDay = new ReservationDay(day);
        final OrderGroup orderGroup = new OrderGroup(List.of(new Order(Menu.CHOCOLATE_CAKE, new OrderCount(1))));
        final DiscountPrice expected = new DiscountPrice(new Price(expectedPrice));

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
        final ReservationDay reservationDay = new ReservationDay(3);
        final OrderGroup orderGroup = new OrderGroup(
                List.of(
                        new Order(Menu.CHOCOLATE_CAKE, new OrderCount(1)),
                        new Order(Menu.ICE_CREAM, new OrderCount(1))
                )
        );
        final DiscountPrice expected = new DiscountPrice(new Price(2_023 * 2));

        //when
        final WeekDayDiscount weekDayDiscount = new WeekDayDiscount(reservationDay, orderGroup);
        final DiscountPrice actual = weekDayDiscount.getDiscountPrice();

        //then
        assertThat(expected).isEqualTo(actual);
    }
}
