package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

    private DateUtil() {
        // No instances
    }

    public static boolean isValidDate(
            final int year,
            final int month,
            final int day
    ) {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        format.setLenient(false);

        try {
            format.parse("%d/%d/%d".formatted(year, month, day));
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
