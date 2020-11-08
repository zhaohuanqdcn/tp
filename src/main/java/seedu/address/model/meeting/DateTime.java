package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Meeting's date and time.
 * Guarantee: immutable
 */
public class DateTime {

    public static final String MESSAGE_CONSTRAINTS = "The date and time should be of the format d/M/yy HHmm. "
            + "For example, 12/3/20 1545";

    private static DateTimeFormatter dateInputFormat = DateTimeFormatter.ofPattern("d/M/yy HHmm");
    private static DateTimeFormatter dateOutputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy h.mma");

    public final LocalDateTime value;

    /**
     * Constructs an {@code DateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        value = LocalDateTime.parse(dateTime, dateInputFormat);
    }

    private DateTime(LocalDateTime time) {
        requireNonNull(time);
        value = time;
    }

    /**
     * Return true if string can be formatted to a LocalDateTime object
     */
    public static boolean isValidDateTime(String dateTime) {
        try {
            dateInputFormat.parse(dateTime);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String getDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, d MMMM, uuuu");

        return dateFormatter.format(value);
    }

    public static DateTimeFormatter getDateInputFormat() {
        return dateInputFormat;
    }

    public DateTimeFormatter getDateOutputFormat() {
        return dateOutputFormat;
    }

    public LocalDateTime getValue() {
        return value;
    }

    public DateTime getNextOccurrence(Recurrence recurrence, int index) {
        assert index >= 0;
        if (recurrence == Recurrence.NONE || index == 0) {
            return this;
        } else {
            if (recurrence == Recurrence.DAILY) {
                return new DateTime(value.plusDays(index));
            } else if (recurrence == Recurrence.WEEKLY) {
                return new DateTime(value.plusWeeks(index));
            } else {
                return new DateTime(value.plusMonths(index));
            }
        }
    }

    @Override
    public String toString() {
        return value.format(dateOutputFormat);
    }

    @Override
    public boolean equals(Object other) {
        assert value != null : "value of this DateTime is null";
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && value.equals(((DateTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        assert value != null : "value of DateTime is null";
        return value.hashCode();
    }

    public String getStartTime() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");
        return timeFormatter.format(value);
    }

    public String getEndTime(Duration duration) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");
        return timeFormatter.format(value
                .plusHours(duration.getHours())
                .plusMinutes(duration.getMinutes()));
    }

    public DateTime copy() {
        return new DateTime(value);
    }
}
