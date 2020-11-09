package seedu.address.testutil;

import static seedu.address.testutil.MeetingBuilder.DEFAULT_HOURS;
import static seedu.address.testutil.MeetingBuilder.DEFAULT_LOCATION;
import static seedu.address.testutil.MeetingBuilder.DEFAULT_MINUTES;
import static seedu.address.testutil.MeetingBuilder.DEFAULT_RECURRENCE;
import static seedu.address.testutil.MeetingBuilder.DEFAULT_TITLE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import seedu.address.model.AddressBook;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Duration;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Recurrence;
import seedu.address.model.meeting.Title;

/**
 * A utility class containing a list of {@code Meeting} objects to be used in tests.
 */
public class TypicalMeetings {

    public static final Meeting DISCUSSION = new MeetingBuilder().withTitle("disscussion")
            .withLocation("123, Jurong West Ave 6, #08-111")
            .withDateTime("12/2/12 1201")
            .withDuration(new Duration(1, 20))
            .withParticipants(ALICE.getUuid(), BOB.getUuid())
            .build();
    public static final Meeting ROUNDTABLE = new MeetingBuilder().withTitle("Benson Meier")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/1/20 1200")
            .withDuration(new Duration(2, 0))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();

    public static final Meeting CONFLICT_MEETING_ONE = new MeetingBuilder().withTitle("Test Meeting 1")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/12/20 1400")
            .withDuration(new Duration(1, 30))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();

    public static final Meeting CONFLICT_MEETING_TWO = new MeetingBuilder().withTitle("Test Meeting 2")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/12/20 1530")
            .withDuration(new Duration(1, 30))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();

    public static final Meeting CONFLICT_MEETING_THREE = new MeetingBuilder().withTitle("Test Meeting 3")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/12/20 1531")
            .withDuration(new Duration(1, 30))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();



    public static final Meeting CONFLICT_MEETING_FOUR = new MeetingBuilder().withTitle("Test Meeting 4")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/12/20 1540")
            .withDuration(new Duration(1, 30))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();

    public static final Meeting CONFLICT_MEETING_FIVE = new MeetingBuilder().withTitle("Test Meeting 5")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/12/20 1541")
            .withDuration(new Duration(1, 30))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();

    public static final Meeting CONFLICT_MEETING_SIX = new MeetingBuilder().withTitle("Test Meeting 6")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/12/20 1300")
            .withDuration(new Duration(1, 0))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();

    public static final Meeting CONFLICT_MEETING_SEVEN = new MeetingBuilder().withTitle("Test Meeting 7")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/12/20 1300")
            .withDuration(new Duration(0, 59))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();

    public static final Meeting CONFLICT_MEETING_EIGHT = new MeetingBuilder().withTitle("Test Meeting 8")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/12/20 1259")
            .withDuration(new Duration(1, 0))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();

    public static final Meeting CONFLICT_MEETING_NINE = new MeetingBuilder().withTitle("Test Meeting 9")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/12/20 1250")
            .withDuration(new Duration(1, 0))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();

    public static final Meeting CONFLICT_MEETING_TEN = new MeetingBuilder().withTitle("Test Meeting 10")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/12/20 1249")
            .withDuration(new Duration(1, 0))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();

    public static final Meeting CONFLICT_MEETING_ELEVEN = new MeetingBuilder().withTitle("Test Meeting 11")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/12/20 1250")
            .withDuration(new Duration(0, 59))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();

    public static final Meeting CONFLICT_MEETING_TWELVE = new MeetingBuilder().withTitle("Test Meeting 12")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("13/12/20 1250")
            .withDuration(new Duration(0, 59))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();

    public static final Meeting CONFLICT_MEETING_THIRTEEN = new MeetingBuilder().withTitle("Test Meeting 13")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/12/21 1250")
            .withDuration(new Duration(0, 59))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();

    public static final Meeting CONFLICT_MEETING_FOURTEEN = new MeetingBuilder().withTitle("Test Meeting 14")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/12/20 0900")
            .withDuration(new Duration(0, 59))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();

    public static final Meeting CONFLICT_MEETING_FIFTEEN = new MeetingBuilder().withTitle("Test Meeting 15")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withDateTime("12/12/20 1200")
            .withDuration(new Duration(0, 40))
            .withParticipants(ALICE.getUuid(), BOB.getUuid()).build();







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

    /**
     * Returns an {@code AddressBook} with all the typical meetings with contacts.
     */
    public static AddressBook getTypicalAddressBookWithContacts() {
        AddressBook ab = new AddressBook();
        for (Meeting meeting : getTypicalMeetings()) {
            ab.addMeeting(meeting);
        }
        ab.addPerson(ALICE);
        ab.addPerson(BOB);
        return ab;
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(DISCUSSION, ROUNDTABLE));
    }

    public static Meeting getLiveMeeting() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime targetTime = currentTime.plusHours(10);
        DateTime dateTime = new DateTime(targetTime);
        Title title = new Title(DEFAULT_TITLE);
        Duration duration = new Duration(DEFAULT_HOURS, DEFAULT_MINUTES);
        Location location = new Location(DEFAULT_LOCATION);
        Recurrence recurrence = Recurrence.ofNullable(DEFAULT_RECURRENCE);
        Set<UUID> participants = new HashSet<>();
        return new Meeting(title, duration, dateTime, location, recurrence, participants);
    }
}
