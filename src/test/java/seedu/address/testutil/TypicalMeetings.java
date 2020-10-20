package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.meeting.Duration;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Meeting} objects to be used in tests.
 */
public class TypicalMeetings {

    public static final Meeting DISCUSSION = new MeetingBuilder().withTitle("disscussion")
            .withLocation("123, Jurong West Ave 6, #08-111")
            .withDateTime("12/2/12 1201")
            .withDuration(new Duration(1, 20))
            .withParticipants(ALICE, BOB)
            .build();
    public static final Meeting ROUNDTABLE = new MeetingBuilder().withTitle("Benson Meier")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/1/12 1221")
            .withDuration(new Duration(1, 30))
            .withParticipants(ALICE, BOB).build();

    private TypicalMeetings() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical meetings.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Meeting meeting : getTypicalMeetings()) {
            ab.addMeeting(meeting);
        }
        return ab;
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(DISCUSSION, ROUNDTABLE));
    }
}
