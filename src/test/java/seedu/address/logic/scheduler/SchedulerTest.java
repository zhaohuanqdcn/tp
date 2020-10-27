package seedu.address.logic.scheduler;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SchedulerTest {

    @Test
    void convertToDate() {
        LocalDateTime time1 = LocalDateTime.of(2020, 12, 31, 23, 59);
        LocalDateTime time2 = LocalDateTime.of(2020, 2, 29, 0, 0);
        Date date1 = new Date(2020, 12, 31, 24, 59);
        Date date2 = new Date(2020, 2, 29, 0, 0);
        assertEquals(date1, Scheduler.convertToDate(time1));
        assertEquals(date2, Scheduler.convertToDate(time2));
    }

}
