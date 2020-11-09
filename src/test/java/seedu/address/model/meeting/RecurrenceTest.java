package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RecurrenceTest {

    @Test
    public void constructor_null_success() {
        assertEquals(Recurrence.NONE, Recurrence.ofNullable(null));
    }

    @Test
    public void constructor_invalidRecurrence_throwsIllegalArgumentException() {
        String invalidRecurrence = "biweekly";
        assertThrows(IllegalArgumentException.class, () -> Recurrence.ofNullable(invalidRecurrence));
    }

    @Test
    public void isValid() {
        // null recurrence
        assertTrue(Recurrence.isValid(null));

        // invalid recurrence
        assertFalse(Recurrence.isValid("biweekly"));
        assertFalse(Recurrence.isValid("daily monthly"));

        // valid recurrence
        assertTrue(Recurrence.isValid(""));
        assertTrue(Location.isValidLocation("monthly"));
    }

    @Test
    public void ofNullable() {
        assertEquals(Recurrence.NONE, Recurrence.ofNullable(""));
        assertEquals(Recurrence.NONE, Recurrence.ofNullable(null));
        assertEquals(Recurrence.DAILY, Recurrence.ofNullable("daily"));
        assertEquals(Recurrence.WEEKLY, Recurrence.ofNullable("weekly"));
        assertEquals(Recurrence.MONTHLY, Recurrence.ofNullable("monthly"));
    }
}
