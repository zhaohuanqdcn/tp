package seedu.address.model.meeting;

import java.util.Objects;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Meeting's duration.
 * Guarantees: immutable; is valid as declared in {@link #isValidDuration(long)}
 */
public class Duration {

    public static final long MAX_MINUTES = 59;
    public static final String MESSAGE_CONSTRAINTS = "Number of minutes should not be more than " + MAX_MINUTES;

    public final long hours;
    public final long minutes;

    /**
     * Constructs a {@code Duration}.
     *
     * @param hours The number of hours.
     * @param minutes The number of minutes.
     */
    public Duration(long hours, long minutes) {
        requireAllNonNull(hours, minutes);
        checkArgument(isValidDuration(minutes), MESSAGE_CONSTRAINTS);
        this.hours = hours;
        this.minutes = minutes;
    }

    /**
     * Returns true if a given number of minutes is valid.
     */
    public static boolean isValidDuration(long minutes) {
        return minutes <= MAX_MINUTES;
    }

    @Override
    public String toString() {
        String temp = hours + "hours";
        if (minutes == 0) {
            return temp;
        } else {
            return temp + " " + minutes + "minutes";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Duration // instanceof handles nulls
                && hours == ((Duration) other).hours && minutes == ((Duration) other).minutes); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes);
    }

}
