package util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("날짜 유틸리티 클래스")
class DateUtilTest {
    @DisplayName("유효한 날짜면 True 를 반환한다.")
    @ParameterizedTest(name = "{0}년 {1}월 {2}일 => {3}")
    @CsvSource(value = {
            "2023:12:0:false",
            "2023:12:1:true",
            "2023:12:31:true",
            "2023:12:32:false"
    }, delimiter = ':')
    void Should_Return_When_Valid_Date_Given(final int year, final int month, final int day, final boolean expected) {
        //when
        final boolean actual = DateUtil.isValidDate(year, month, day);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
