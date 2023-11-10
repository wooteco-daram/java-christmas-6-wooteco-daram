package menu;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("가격 테스트")
class PriceTest {
    @Test
    @DisplayName("가격이 음수이면 IllegalArgumentException 예외를 발생한다.")
    void Should_IllegalArgumentExceptionThrown_When_Price_Is_Negative() {
        //when, then
        assertThatThrownBy(() -> new Price(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("가격은 음수가 될 수 없습니다.");
    }
}
