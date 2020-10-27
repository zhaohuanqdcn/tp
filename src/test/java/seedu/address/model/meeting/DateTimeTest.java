package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "1201";
        assertThrows(DateTimeParseException.class, () -> new DateTime(invalidDateTime));
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
        assertThrows(DateTimeParseException.class, () ->
                DateTime.isValidDateTime("1/2/2 1111")); // year is not two digits
        assertThrows(DateTimeParseException.class, () ->
                DateTime.isValidDateTime("112342325")); // does not follow format
        assertThrows(DateTimeParseException.class, () ->
                DateTime.isValidDateTime("1/2/10 1160")); // does not follow format

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
}
