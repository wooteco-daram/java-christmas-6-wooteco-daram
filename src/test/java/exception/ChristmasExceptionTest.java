package exception;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Christmas 예외")
class ChristmasExceptionTest extends NsTest {
    @Test
    @DisplayName("예외 생성 시 \"[ERROR]\"를 맨 앞에 붙여야 합니다.")
    void Should_Print_Error_Prefix_When_Christmas_Exception_Create() {
        //given
        final String errorMessage = "Error Message";
        final String expected = "[ERROR] " + errorMessage;

        //when
        new ChristmasException(errorMessage);

        //then
        assertThat(output()).contains(expected);
    }

    @Test
    @DisplayName("상수 예외 메시지를 전달할 시 상수 예외 메시지를 출력해야 합니다.")
    void Should_Print_Error_Message_When_Raise_By_Error_Message_Enum() {
        //given
        final String expected = "[ERROR] " + ErrorMessage.INVALID_MENU.getMessage();

        //when
        ChristmasException.of(ErrorMessage.INVALID_MENU);

        //then
        assertThat(output()).contains(expected);
    }

    @Override
    protected void runMain() {}
}