package menu;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.menu.Menu;
import exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("메뉴 테스트")
class MenuTest {
    @Test
    @DisplayName("메뉴 이름으로 메뉴를 찾을 수 있다.")
    void Should_Get_Menu_When_Find_From_Exist_Name() {
        //given
        final String name = "양송이수프";
        final Menu expected = Menu.PINE_MUSHROOM_SOUP;

        //when
        final Menu actual = Menu.findByName(name);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("메뉴 이름을 찾을 수 없으면 IllegalArgumentException 예외를 발생시킨다.")
    void Should_Get_UNKNOWN_When_Name_Not_Found() {
        //when, then
        assertThatThrownBy(() -> Menu.findByName("NOT_FOUND"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_MENU.getMessage());
    }
}
