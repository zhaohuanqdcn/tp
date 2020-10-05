package seedu.address.model.meeting;

import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        LocalDateTime invalidDateTime = null;
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDateTime));
    }
}
