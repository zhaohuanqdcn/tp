package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

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

    /**
     * Checks whether a meeting conflicts with other meetings in the schedule.
     */
    public Pair<Boolean, Optional<Meeting>> checkConflict(Meeting meeting, int interval) {
        // the list should be sorted at all time, however sort again just to ensure it is sorted.
        sort();
        LocalDateTime currentMeetingDateTime = meeting.getDateTime().value;
        Predicate<Meeting> isBeforeCurrentMeetingPredicate = x -> x.getDateTime().value
                .isBefore(currentMeetingDateTime);
        Predicate<Meeting> isAfterOrEqualCurrentMeetingPredicate = x -> x.getDateTime().value
                .isAfter(currentMeetingDateTime) || x.getDateTime().value.equals(currentMeetingDateTime);
        BinaryOperator<Meeting> storeLast = (x, y) -> y;

        Optional<Meeting> nearestMeetingBeforeCurrent = internalList.stream()
                                                                    .filter(isBeforeCurrentMeetingPredicate)
                                                                    .reduce(storeLast);

        Optional<Meeting> nearestMeetingAfterOrEqualCurrent = internalList.stream()
                                                                          .filter(isAfterOrEqualCurrentMeetingPredicate)
                                                                          .findFirst();

        if (checkMeetingBefore(nearestMeetingBeforeCurrent, meeting, interval)) {
            return new Pair<> (true, nearestMeetingBeforeCurrent);
        }

        if (checkMeetingAfter(nearestMeetingAfterOrEqualCurrent, meeting, interval)) {
            return new Pair<>(true, nearestMeetingAfterOrEqualCurrent);
        }

        return new Pair<>(false, Optional.empty());


    }

    private boolean checkMeetingBefore(Optional<Meeting> nearestMeetingBefore, Meeting toAddMeeting, int interval) {
        if (nearestMeetingBefore.isPresent()) {
            Meeting meetingBefore = nearestMeetingBefore.get();
            LocalDateTime meetingBeforeDateTime = meetingBefore.getDateTime().value;
            Duration meetingBeforeDuration = meetingBefore.getDuration();
            long totalMinutes = meetingBeforeDuration.hours * 60 + meetingBeforeDuration.minutes + interval;
            LocalDateTime nextMeetingAvailableDateTime = meetingBeforeDateTime.plusMinutes(totalMinutes);

            LocalDateTime currentMeetingDateTime = toAddMeeting.getDateTime().value;

            if (currentMeetingDateTime.equals(nextMeetingAvailableDateTime)
                    || currentMeetingDateTime.isBefore(nextMeetingAvailableDateTime)) {
                return true;
            }

        }
        return false;
    }

    private boolean checkMeetingAfter(Optional<Meeting> meetingAfterOrEqual, Meeting toAddMeeting, int interval) {
        if (meetingAfterOrEqual.isPresent()) {
            Meeting meetingToCheck = meetingAfterOrEqual.get();
            LocalDateTime meetingToCheckDateTime = meetingToCheck.getDateTime().value;
            if (meetingToCheckDateTime.equals(toAddMeeting.getDateTime().value)) {
                return true;
            }

            LocalDateTime currentMeetingDateTime = toAddMeeting.getDateTime().value;
            Duration currentMeetingDuration = toAddMeeting.getDuration();
            long totalMinutes = currentMeetingDuration.hours * 60 + currentMeetingDuration.minutes + interval;
            LocalDateTime currentMeetingEndTime = currentMeetingDateTime.plusMinutes(totalMinutes);

            if (meetingToCheckDateTime.equals(currentMeetingEndTime)
                    || meetingToCheckDateTime.isBefore(currentMeetingEndTime)) {
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
            LocalDateTime dateTimeOne = meetingOne.getDateTime().getValue();
            LocalDateTime dateTimeTwo = meetingTwo.getDateTime().getValue();
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

    /**
     * Returns the first future meeting, if any.
     */
    public Meeting getNextMeeting(long offset) {
        Optional<Meeting> first = internalList.stream().filter(meeting -> meeting
                .isFutureMeeting(LocalDateTime.now().plusMinutes(offset))).findFirst();
        return first.orElse(null);
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


    public static class Pair<T, R> {

        private final T valueOne;
        private final R valueTwo;

        /**
         * Constructs a 2-tuple that holds any two value
         */
        public Pair(T valueOne, R valueTwo) {
            requireNonNull(valueOne);
            requireNonNull(valueTwo);
            this.valueOne = valueOne;
            this.valueTwo = valueTwo;
        }

        public T getLeftValue() {
            return valueOne;
        }

        public R getRightValue() {
            return valueTwo;
        }

        @Override
        public int hashCode() {
            return valueOne.hashCode() ^ valueTwo.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof Pair // instanceof handles nulls
                    && valueOne.equals(((Pair<?, ?>) other).getLeftValue()) // state check
                    && valueTwo.equals(((Pair<?, ?>) other).getRightValue()));
        }

    }
}
