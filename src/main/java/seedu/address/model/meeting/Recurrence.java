package seedu.address.model.meeting;

/**
 * Represents a Meeting's recurrence.
 */
public enum Recurrence {
    NONE, DAILY, WEEKLY, MONTHLY;

    public static final String MESSAGE_CONSTRAINTS = "Recurrence can be one of daily, weekly or monthly, "
            + "with a positive integer no more than 20 followed after '/' indicating the number of recurrences.";

    /**
     * Check if a given string {@code recur} can be formatted in to a Recurrence object
     */
    public static boolean isValid(String recur) {
        return recur == null || recur.equals("daily") || recur.equals("weekly")
                || recur.equals("monthly") || recur.isEmpty();
    }

    /**
     * Constructor of Recurrence giving {@code recur}
     */
    public static Recurrence ofNullable(String recur) {
        if (recur == null) {
            return NONE;
        }
        switch (recur) {
        case "":
            return NONE;
        case "daily":
            return DAILY;
        case "monthly":
            return MONTHLY;
        case "weekly":
            return WEEKLY;
        default:
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return this == MONTHLY ? "monthly"
                : this == DAILY ? "daily"
                : this == WEEKLY ? "weekly"
                : "";
    }

    public Recurrence copy() {
        return this;
    }
}
