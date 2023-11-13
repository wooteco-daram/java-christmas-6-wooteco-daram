package menu;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("메뉴판 테스트")
class MenuBoardTest {
    @ParameterizedTest(name = "{0} 메뉴가 {1}개 있다.")
    @MethodSource("provideMenuAndOverlapCount")
    @DisplayName("메뉴판의 메뉴 카테고리 목록과 겹치는 메뉴 개수를 출력한다.")
    void Should_Return_Overlapped_Count_When(
            final MenuBoard menuBoard,
            final long expected
    ) {
        //given
        final List<Menu> from = List.of(
                Menu.PINE_MUSHROOM_SOUP, Menu.TAPAS,    // APPETIZER
                Menu.CHOCOLATE_CAKE,                    // DESSERT
                Menu.ZERO_COKE                          // DRINK
        );

        //when
        final long actual = menuBoard.countOverlap(from);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideMenuAndOverlapCount() {
        return Stream.of(
            Arguments.of(MenuBoard.APPETIZER, 2),
                Arguments.of(MenuBoard.MAIN, 0),
                Arguments.of(MenuBoard.DESSERT, 1),
                Arguments.of(MenuBoard.DRINK, 1)
        );
    }
}
