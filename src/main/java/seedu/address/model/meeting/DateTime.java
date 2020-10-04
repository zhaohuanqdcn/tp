package seedu.address.model.meeting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Meeting's date and time.
 * Guarantee: immutable
 */
public class DateTime {

    public static final String MESSAGE_CONSTRAINTS = "The date and time should be of the format dd/mm/yy hhmm. "
            + "For example, 12/3/20 1545";

    private static DateTimeFormatter dateInputFormat = DateTimeFormatter.ofPattern("d/M/yy HHmm");
    private static DateTimeFormatter dateOutputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy h.mma");

    public final LocalDateTime value;

    /**
     * Constructs an {@code DateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public DateTime(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        value = dateTime;
    }

    public static DateTimeFormatter getDateInputFormat() {
        return dateInputFormat;
    }

    public static DateTimeFormatter getDateOutputFormat() {
        return dateOutputFormat;
    }

    @Override
    public String toString() {
        return value.format(dateOutputFormat);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && value.equals(((DateTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
