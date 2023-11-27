package domain.event.badge;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.price.DiscountPrice;
import java.util.stream.Stream;
import domain.price.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EventBadgeTypeTest {

    @DisplayName("특정 금액 이상일 시 이벤트 배지를 부여합니다.")
    @ParameterizedTest(name = "{0} 이상일 시 {1} 배지를 부여합니다.")
    @MethodSource("providePriceAndEventBadgeType")
    void Should_Badge_When_Price_Is_Greater_Than_Or_Equal_To_Price(
            final DiscountPrice discountPrice,
            final EventBadgeType expected
    ) {
        //given
        //when
        final EventBadgeType actual = EventBadgeType.findByDiscountPrice(discountPrice);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> providePriceAndEventBadgeType() {
        return Stream.of(
                Arguments.of(DiscountPrice.empty(), EventBadgeType.NONE),
                Arguments.of(DiscountPrice.from(5_000), EventBadgeType.STAR),
                Arguments.of(DiscountPrice.from(10_000), EventBadgeType.TREE),
                Arguments.of(DiscountPrice.from(20_000), EventBadgeType.SANTA)
        );
    }
}
