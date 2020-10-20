package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "1201";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDateTime));
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
}
