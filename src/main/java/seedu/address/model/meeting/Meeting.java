package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;

/**
 * Represents a Meeting in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Meeting {

    // Identity fields
    private final Title title;
    private final DateTime dateTime;

    // Data fields
    private final Duration duration;
    private final Location location;
    private final Set<Person> participants = new HashSet<>();
    private final Recurrence recurrence;

    /**
     * Create Meeting with Location and Recurrence.
     * Every field must be present and not null.
     */
    public Meeting(Title title, Duration duration, DateTime dateTime,
                   Location location, Recurrence recurrence, Set<Person> participants) {
        requireAllNonNull(title, duration, dateTime, location, participants, recurrence);
        this.title = title;
        this.duration = duration;
        this.dateTime = dateTime;
        this.location = location;
        this.participants.addAll(participants);
        this.recurrence = recurrence;
    }

    /**
     * Create Meeting without Location.
     * Every field must be present and not null.
     */
    public Meeting(Title title, Duration duration, DateTime dateTime,
                   Recurrence recurrence, Set<Person> participants) {
        requireAllNonNull(title, duration, dateTime, participants);
        this.title = title;
        this.duration = duration;
        this.dateTime = dateTime;
        this.location = null;
        this.participants.addAll(participants);
        this.recurrence = recurrence;
    }

    public Title getTitle() {
        return title;
    }

    public Duration getDuration() {
        return duration;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Location getLocation() {
        return location;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    /**
     * Get all recurrences as a list
     */
    public List<Meeting> getRecurrencesAsList() {
        if (getRecurrence() == Recurrence.NONE) {
            return Arrays.asList(this);
        }
        List<Meeting> recurrences = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Meeting next =
                    new Meeting(getTitle(), getDuration(),
                            getDateTime().getNextOccurrence(getRecurrence(), i),
                            getLocation(), getRecurrence(),
                            Set.copyOf(getParticipants()));
            recurrences.add(next);
        }
        return recurrences;
    }

    /**
     * Returns true if both meetings have the same title and recurrence
     */
    public boolean isSameRecurringMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getTitle().equals(getTitle())
                && otherMeeting.getRecurrence() == getRecurrence();
    }

    /**
     * Returns an immutable person set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Person> getParticipants() {
        return Collections.unmodifiableSet(participants);
    }

    /**
     * Returns true if both meetings of the same title have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two meetings.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getTitle().equals(getTitle())
                && (otherMeeting.getDateTime().equals(getDateTime()));
    }

    public void addParticipant(Person person) {
        this.participants.add(person);
    }

    /**
     * Delete a participant from the set based on its index.
     * @param index Index of the participant to be deleted.
     */
    public void delParticipant(Index index) {
        List<Person> personList = new ArrayList<>(this.participants);
        int length = this.participants.size();
        assert length > index.getZeroBased() : "index is invalid";
        Person personToDelete = personList.get(length - 1 - index.getZeroBased());
        this.participants.remove(personToDelete);
    }

    /**
     * Returns true if both meetings have the same identity and data fields.
     * This defines a stronger notion of equality between two meetings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getTitle().equals(getTitle())
                && otherMeeting.getDuration().equals(getDuration())
                && otherMeeting.getDateTime().equals(getDateTime())
                && otherMeeting.getLocation().equals(getLocation())
                && otherMeeting.getParticipants().equals(getParticipants())
                && otherMeeting.getRecurrence() == getRecurrence();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, dateTime, duration, location, recurrence, participants);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Date and Time: ")
                .append(getDateTime())
                .append(" Duration: ")
                .append(getDuration())
                .append(" Location: ")
                .append(getLocation())
                .append(" Recurrence: ")
                .append(getRecurrence())
                .append(" Participants: ");
        getParticipants().forEach(builder::append);
        return builder.toString();
    }

}
