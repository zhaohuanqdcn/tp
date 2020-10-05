package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_ROUNDTABLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_ROUNDTABLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_ROUNDTABLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARTICIPANT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_ROUNDTABLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.DISCUSSION;
import static seedu.address.testutil.TypicalMeetings.ROUNDTABLE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

public class MeetingTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Meeting meeting = new MeetingBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> meeting.getParticipants().remove(0));
    }

    @Test
    public void isSameMeeting() {
        // same object -> returns true
        assertTrue(DISCUSSION.isSameMeeting(DISCUSSION));

        // null -> returns false
        assertFalse(DISCUSSION.isSameMeeting(null));

        // different dateTime -> returns false
        Meeting editedDiscussion = new MeetingBuilder(DISCUSSION).withDateTime(VALID_DATETIME_ROUNDTABLE).build();
        assertFalse(DISCUSSION.isSameMeeting(editedDiscussion));

        // different title -> returns false
        editedDiscussion = new MeetingBuilder(DISCUSSION).withTitle(VALID_TITLE_ROUNDTABLE).build();
        assertFalse(DISCUSSION.isSameMeeting(editedDiscussion));

        // same title, same dateTime, different attributes -> returns true
        editedDiscussion = new MeetingBuilder(DISCUSSION).withDuration(VALID_DURATION_ROUNDTABLE)
                .withLocation(VALID_LOCATION_ROUNDTABLE)
                .withParticipants(VALID_PARTICIPANT_BOB).build();
        assertTrue(DISCUSSION.isSameMeeting(editedDiscussion));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Meeting discussionCopy = new MeetingBuilder(DISCUSSION).build();
        assertTrue(DISCUSSION.equals(discussionCopy));

        // same object -> returns true
        assertTrue(DISCUSSION.equals(DISCUSSION));

        // null -> returns false
        assertFalse(DISCUSSION.equals(null));

        // different type -> returns false
        assertFalse(DISCUSSION.equals(5));

        // different meeting -> returns false
        assertFalse(DISCUSSION.equals(ROUNDTABLE));

        // different title -> returns false
        Meeting editedDiscussion = new MeetingBuilder(DISCUSSION).withTitle(VALID_TITLE_ROUNDTABLE).build();
        assertFalse(DISCUSSION.equals(editedDiscussion));

        // different duration -> returns false
        editedDiscussion = new MeetingBuilder(DISCUSSION).withDuration(VALID_DURATION_ROUNDTABLE).build();
        assertFalse(DISCUSSION.equals(editedDiscussion));

        // different dateTime -> returns false
        editedDiscussion = new MeetingBuilder(DISCUSSION).withDateTime(VALID_DATETIME_ROUNDTABLE).build();
        assertFalse(DISCUSSION.equals(editedDiscussion));

        // different location -> returns false
        editedDiscussion = new MeetingBuilder(DISCUSSION).withLocation(VALID_LOCATION_ROUNDTABLE).build();
        assertFalse(DISCUSSION.equals(editedDiscussion));

        // different participants -> returns false
        editedDiscussion = new MeetingBuilder(DISCUSSION).withParticipants(VALID_PARTICIPANT_BOB).build();
        assertFalse(DISCUSSION.equals(editedDiscussion));
    }
}
