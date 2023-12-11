package exception;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Christmas 예외")
class GlobalExceptionTest {
    @Test
    @DisplayName("예외 발생 시 IllegalArgumentException 타입이고 오류 메시지를 포함합니다.")
    void Should_Print_Error_Prefix_When_Christmas_Exception_Create() {
        //given
        final String expected = ErrorMessage.INVALID_MENU.getMessage();

        //when, then
        assertThatThrownBy(() -> { throw GlobalException.from(ErrorMessage.INVALID_MENU); })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expected);
    }
}
