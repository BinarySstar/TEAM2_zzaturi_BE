package goorm.zzaturi.global.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;

public class DateUtils {

    public static LocalDateTime getStartOfWeek() {
        return LocalDate.now()
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            .atStartOfDay();
    }

    public static LocalDateTime getEndOfWeek() {
        return LocalDate.now()
            .with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
            .atStartOfDay();
    }
}
