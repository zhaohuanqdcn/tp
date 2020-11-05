package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DurationTest {

    @Test
    public void constructor_exceedingMinutes_throwsIllegalArgumentException() {
        long invalidDuration = Duration.MAX_MINUTES + 1;
        assertThrows(IllegalArgumentException.class, () -> new Duration(0, invalidDuration));
    }

    @Test
    public void constructor_negativeHours_throwsIllegalArgumentException() {
        long minutes = Duration.MAX_MINUTES;
        assertThrows(IllegalArgumentException.class, () -> new Duration(-10, minutes));
    }

    @Test
    public void isValidDuration() {

        // invalid minutes
        assertFalse(Duration.isValidDuration(1, Duration.MAX_MINUTES + 1)); // minutes is more than the max minutes
        assertFalse(Duration.isValidDuration(0, 0)); // zero duration is not valid
        assertFalse(Duration.isValidDuration(-1, 30)); // negative hours
        assertFalse(Duration.isValidDuration(1, -10)); // negative minutes

        // valid minutes
        assertTrue(Duration.isValidDuration(1, 0));
        assertTrue(Duration.isValidDuration(1, 29));
        assertTrue(Duration.isValidDuration(1, Duration.MAX_MINUTES));
        assertTrue(Duration.isValidDuration(24, Duration.MAX_MINUTES));
    }
}
