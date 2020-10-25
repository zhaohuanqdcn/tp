package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.model.meeting.Meeting;

/**
 * A utility class to help with building recurring Meeting objects.
 */
public class RecurringMeeting {
    public static List<Meeting> getRecurrencesAsList(Meeting meeting) {
        List<Meeting> recurrences = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Meeting next =
                    new Meeting(meeting.getTitle(), meeting.getDuration(),
                            meeting.getDateTime().getNextOccurrence(meeting.getRecurrence(), i),
                            meeting.getLocation(), meeting.getRecurrence(),
                            Set.copyOf(meeting.getParticipants()));
            recurrences.add(next);
        }
        return recurrences;
    }

}
