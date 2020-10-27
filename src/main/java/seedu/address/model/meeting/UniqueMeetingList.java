package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;


/**
 * A list of meetings that enforces uniqueness between its elements and does not allow nulls.
 * A meeting is considered unique by comparing using {@code Meeting#isSameMeeting(Meeting)}.
 * As such, adding and updating of meetings uses Meeting#isSameMeeting(Meeting) for equality so as to ensure that
 * the meeting being added or updated is unique in terms of identity in the UniqueMeetingList. However,
 * the removal of a meeting uses Meeting#equals(Object) so as to ensure that the meeting with exactly
 * the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Meeting#isSameMeeting(Meeting)
 */
public class UniqueMeetingList implements Iterable<Meeting> {

    private final ObservableList<Meeting> internalList = FXCollections.observableArrayList();
    private final ObservableList<Meeting> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent meeting as the given argument.
     */
    public boolean contains(Meeting toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameMeeting);
    }

    /**
     * Adds a meeting to the list.
     * The meeting must not already exist in the list.
     */
    public void add(Meeting toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMeetingException();
        }
        internalList.add(toAdd);
    }

    public boolean checkConflict(Meeting meeting, int interval) {

        LocalDate currentMeetingLocalDate = meeting.getDateTime().value.toLocalDate();
        Predicate<Meeting> localDatePredicate = x -> x.getDateTime().value.toLocalDate()
                .equals(currentMeetingLocalDate);

        Function<Meeting, Pair<LocalTime, LocalTime>> meetingToStartEndTimeMapper = x -> {
            LocalTime start = x.getDateTime().value.toLocalTime();
            Duration duration = x.getDuration();
            long totalMinutesOfMeeting = duration.hours * 60 + duration.minutes;
            LocalTime end = start.plusMinutes(totalMinutesOfMeeting);
            return new Pair<>(start, end);
        };

        List<Pair<LocalTime, LocalTime>> startEndList = internalList.stream()
                                                                    .filter(localDatePredicate)
                                                                    .map(meetingToStartEndTimeMapper)
                                                                    .collect(Collectors.toList());

        int totalMinutesinDay = 24 * 60;
        // each array element represent a minute in a day
        boolean[] dayMinutesArray = new boolean[totalMinutesinDay];

        fillArray(startEndList, dayMinutesArray, interval);

        return checkMeetingWithArray(dayMinutesArray, meeting);
    }

    private int convertLocalTimeToArrayIndex(LocalTime localTime) {
        return localTime.getHour()*60 + localTime.getMinute();
    }

    private void fillArray(List<Pair<LocalTime, LocalTime>> pairList, boolean[] dayMinutesArray, int interval) {
        for (Pair<LocalTime, LocalTime> startEndTime : pairList) {
            LocalTime start = startEndTime.getValueOne();
            LocalTime end = startEndTime.getValueTwo();
            int startIndex = convertLocalTimeToArrayIndex(start) - interval;
            int endIndex = convertLocalTimeToArrayIndex(end) + interval;

            for(int i = startIndex; i <= endIndex; i++) {
                dayMinutesArray[i] = true;
            }
        }

    }

    public boolean checkMeetingWithArray(boolean[] dayMinutesArray, Meeting meeting) {
        LocalTime start = meeting.getDateTime().value.toLocalTime();
        LocalTime end = start.plusMinutes(meeting.getDuration().hours * 60 + meeting.getDuration().minutes);
        int startIndex = convertLocalTimeToArrayIndex(start);
        int endIndex = convertLocalTimeToArrayIndex(end);

        for(int i = startIndex; i <= endIndex; i++) {
            if (dayMinutesArray[i]) {
                return true;
            }
        }

        return false;
    }

    /**
     * Sorts all meetings in the list according to Date and Time.
     * Tie break by comparing meeting's title in chronological order.
     */
    public void sort() {
        Comparator<Meeting> meetingComparator = (meetingOne, meetingTwo) -> {
            LocalDateTime dateTimeOne = meetingOne.getDateTime().value;
            LocalDateTime dateTimeTwo = meetingTwo.getDateTime().value;
            if (dateTimeOne.equals(dateTimeTwo)) {
                return meetingOne.getTitle().value.compareTo(meetingTwo.getTitle().value);
            } else {
                return dateTimeOne.compareTo(dateTimeTwo);
            }
        };
        internalList.sort(meetingComparator);
    }

    /**
     * Replaces the meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the list.
     * The meeting identity of {@code editedMeeting} must not be the same as another existing meeting in the list.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MeetingNotFoundException();
        }

        if (!target.isSameMeeting(editedMeeting) && contains(editedMeeting)) {
            throw new DuplicateMeetingException();
        }

        internalList.set(index, editedMeeting);
    }

    /**
     * Removes the equivalent meeting from the list.
     * The meeting must exist in the list.
     */
    public void remove(Meeting toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MeetingNotFoundException();
        }
    }

    /**
     * Gets all recurrences of a meeting from the list.
     * The meeting must exist in the list.
     */
    public FilteredList<Meeting> getRecurringMeetings(Meeting toRemove) {
        requireNonNull(toRemove);
        return internalList.filtered(toRemove::isSameRecurringMeeting);
    }

    public void setMeetings(UniqueMeetingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code meetings}.
     * {@code meetings} must not contain duplicate meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        requireAllNonNull(meetings);
        if (!meetingsAreUnique(meetings)) {
            throw new DuplicateMeetingException();
        }

        internalList.setAll(meetings);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Meeting> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }


    @Override
    public Iterator<Meeting> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueMeetingList // instanceof handles nulls
                && internalList.equals(((UniqueMeetingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code meetings} contains only unique meetings.
     */
    private boolean meetingsAreUnique(List<Meeting> meetings) {
        for (int i = 0; i < meetings.size() - 1; i++) {
            for (int j = i + 1; j < meetings.size(); j++) {
                if (meetings.get(i).isSameMeeting(meetings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


    public static class Pair<T,R> {

        private final T valueOne;
        private final R valueTwo;

        public Pair(T valueOne, R valueTwo) {
            requireNonNull(valueOne);
            requireNonNull(valueTwo);
            this.valueOne = valueOne;
            this.valueTwo = valueTwo;
        }

        public T getValueOne() { return valueOne; }
        public R getValueTwo() { return valueTwo; }

        @Override
        public int hashCode() { return valueOne.hashCode() ^ valueTwo.hashCode(); }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof Pair // instanceof handles nulls
                    && valueOne.equals(((Pair<T, R>) other).getValueOne()) // state check
                    && valueTwo.equals(((Pair<T, R>) other).getValueTwo()));
        }

    }
}
