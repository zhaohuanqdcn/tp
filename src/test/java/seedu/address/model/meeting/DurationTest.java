package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DurationTest {

    @Test
    public void constructor_invalidDuration_throwsIllegalArgumentException() {
        long invalidDuration = Duration.MAX_MINUTES + 1;
        assertThrows(IllegalArgumentException.class, () -> new Duration(0, invalidDuration));
    }

    @Test
    public void isValidDuration() {

        // invalid minutes
        assertFalse(Duration.isValidDuration(1, Duration.MAX_MINUTES + 1)); // minutes is more than the max minutes

        // valid minutes - less than or equal to max minutes
        assertTrue(Duration.isValidDuration(1, 0));
        assertTrue(Duration.isValidDuration(1, 29));
        assertTrue(Duration.isValidDuration(1, Duration.MAX_MINUTES));
    }
}
