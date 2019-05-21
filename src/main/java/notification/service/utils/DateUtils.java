package notification.service.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.TimeZone;

public final class DateUtils {
    public static final Calendar DEFAULT_UTC = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

    private DateUtils() {
    }

    public static LocalDateTime convertToLocalDateTime(Timestamp dateToConvert) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(dateToConvert.getTime()), ZoneOffset.systemDefault());
    }

    public static Timestamp convertToDate(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }
}
