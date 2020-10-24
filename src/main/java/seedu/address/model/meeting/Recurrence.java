package seedu.address.model.meeting;

/**
 * Represents a Meeting's recurrence.
 */
public enum Recurrence {
    NONE, DAILY, WEEKLY, MONTHLY;

    public static String MESSAGE_CONSTRAINTS = "Recurrence can be one of daily, weekly or monthly. ";

    public static boolean isValid(String recur){
        return recur.equals("daily") || recur.equals("weekly")
                || recur.equals("monthly") || recur.isEmpty();
    }

    public static Recurrence ofNullable(String recur) {
        return recur == null || recur.isEmpty() ? NONE
                : recur.equals("daily") ? DAILY
                : recur.equals("weekly") ? WEEKLY
                : MONTHLY;
    }

    @Override
    public String toString() {
        return this == MONTHLY ? "monthly"
                : this == DAILY ? "daily"
                : this == WEEKLY ? "weekly"
                : "";
    }

}
