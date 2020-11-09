package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void constructor_nullString_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime((String) null));
    }

    @Test
    public void constructor_nullLocalDateTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime((LocalDateTime) null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "1201";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDateTime));
    }

    @Test
    public void getNextRecurrence() {
        DateTime dateTime = new DateTime("30/12/20 1500");
        DateTime twoDaysLater = new DateTime("1/1/21 1500");
        DateTime threeWeekLater = new DateTime("20/1/21 1500");
        DateTime twoMonthLater = new DateTime("28/2/21 1500");
        assertEquals(dateTime.getNextOccurrence(Recurrence.DAILY, 2), twoDaysLater);
        assertEquals(dateTime.getNextOccurrence(Recurrence.WEEKLY, 3), threeWeekLater);
        assertEquals(dateTime.getNextOccurrence(Recurrence.MONTHLY, 2), twoMonthLater);
        assertEquals(dateTime.getNextOccurrence(Recurrence.MONTHLY, 0), dateTime);
        assertEquals(dateTime.getNextOccurrence(Recurrence.NONE, 1000), dateTime);
    }

    @Test
    public void isValidDateTime() {
        // null dateTime
        assertThrows(NullPointerException.class, () -> DateTime.isValidDateTime(null));

        // invalid dateTimes
        assertFalse(DateTime.isValidDateTime("1/2/2 1111")); // year is not two digits
        assertFalse(DateTime.isValidDateTime("112342325")); // does not follow format
        assertFalse(DateTime.isValidDateTime("1/2/10 1160")); // does not follow format

        // valid dateTimes
        assertTrue(DateTime.isValidDateTime("1/2/10 1300"));
        assertTrue(DateTime.isValidDateTime("12/12/10 1312"));
        assertTrue(DateTime.isValidDateTime("12/2/10 1302"));
        assertTrue(DateTime.isValidDateTime("2/12/10 1352"));

    }

    @Test
    public void getDate_validDates_equal() {
        String expected = "Thursday, 31 December, 2020";
        DateTime actual = new DateTime("31/12/20 1400");

        assertEquals(expected, actual.getDate());

        expected = "Friday, 1 January, 2021";
        actual = new DateTime("1/1/21 1400");

        assertEquals(expected, actual.getDate());
    }

    @Test
    public void getDate_invalidDates_notEqual() {
        String expected = "Tuesday, 31 December, 2020";
        DateTime actual = new DateTime("30/12/20 1400");

        assertNotEquals(expected, actual.getDate());

        expected = "Friday, 13 January, 2021";
        actual = new DateTime("1/1/22 1400");

        assertNotEquals(expected, actual.getDate());
    }

    @Test
    public void getNextOccurrence() {
        DateTime actual = new DateTime("31/12/20 1400");
        DateTime expected1 = new DateTime("3/1/21 1400");
        DateTime expected2 = new DateTime("31/12/21 1400");
        DateTime expected3 = new DateTime("7/1/21 1400");
        assertEquals(actual.getNextOccurrence(Recurrence.NONE, 0), actual);
        assertEquals(actual.getNextOccurrence(Recurrence.DAILY, 0), actual);
        assertEquals(actual.getNextOccurrence(Recurrence.DAILY, 3), expected1);
        assertEquals(actual.getNextOccurrence(Recurrence.MONTHLY, 12), expected2);
        assertEquals(actual.getNextOccurrence(Recurrence.WEEKLY, 1), expected3);
    }

    @Test
    public void getStartTime() {
        DateTime actual1 = new DateTime("31/12/20 1400");
        DateTime actual2 = new DateTime("31/12/20 0700");
        assertEquals(actual1.getStartTime(), "2:00pm");
        assertEquals(actual2.getStartTime(), "7:00am");
    }

    @Test
    public void getEndTime() {
        DateTime actual = new DateTime("31/12/20 1401");
        Duration duration1 = new Duration(1, 59);
        Duration duration2 = new Duration(11, 0);
        Duration duration3 = new Duration(48, 0);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");
        assertEquals(actual.getEndTime(duration1), timeFormatter.format(LocalTime.of(16, 0)));
        assertEquals(actual.getEndTime(duration2), timeFormatter.format(LocalTime.of(1, 1)));
        assertEquals(actual.getEndTime(duration3), timeFormatter.format(LocalTime.of(14, 1)));
    }
}
