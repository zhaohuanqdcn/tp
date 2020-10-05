package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_ROUNDTABLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARTICIPANT_ALICE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.DISCUSSION;
import static seedu.address.testutil.TypicalMeetings.ROUNDTABLE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.testutil.MeetingBuilder;

public class UniqueMeetingListTest {

    private final UniqueMeetingList UniqueMeetingList = new UniqueMeetingList();

    @Test
    public void contains_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueMeetingList.contains(null));
    }

    @Test
    public void contains_meetingNotInList_returnsFalse() {
        assertFalse(UniqueMeetingList.contains(DISCUSSION));
    }

    @Test
    public void contains_meetingInList_returnsTrue() {
        UniqueMeetingList.add(DISCUSSION);
        assertTrue(UniqueMeetingList.contains(DISCUSSION));
    }

    @Test
    public void contains_meetingWithSameIdentityFieldsInList_returnsTrue() {
        UniqueMeetingList.add(DISCUSSION);
        Meeting editedDiscussion = new MeetingBuilder(DISCUSSION).withLocation(VALID_LOCATION_ROUNDTABLE)
                .withParticipants(VALID_PARTICIPANT_ALICE).build();
        assertTrue(UniqueMeetingList.contains(editedDiscussion));
    }

    @Test
    public void add_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueMeetingList.add(null));
    }

    @Test
    public void add_duplicateMeeting_throwsDuplicateMeetingException() {
        UniqueMeetingList.add(DISCUSSION);
        assertThrows(DuplicateMeetingException.class, () -> UniqueMeetingList.add(DISCUSSION));
    }

    @Test
    public void setMeeting_nullTargetMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueMeetingList.setMeeting(null, DISCUSSION));
    }

    @Test
    public void setMeeting_nullEditedMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueMeetingList.setMeeting(DISCUSSION, null));
    }

    @Test
    public void setMeeting_targetMeetingNotInList_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> UniqueMeetingList.setMeeting(DISCUSSION, DISCUSSION));
    }

    @Test
    public void setMeeting_editedMeetingIsSameMeeting_success() {
        UniqueMeetingList.add(DISCUSSION);
        UniqueMeetingList.setMeeting(DISCUSSION, DISCUSSION);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(DISCUSSION);
        assertEquals(expectedUniqueMeetingList, UniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasSameIdentity_success() {
        UniqueMeetingList.add(DISCUSSION);
        Meeting editedDiscussion = new MeetingBuilder(DISCUSSION).withLocation(VALID_LOCATION_ROUNDTABLE)
                .withParticipants(VALID_PARTICIPANT_ALICE).build();
        UniqueMeetingList.setMeeting(DISCUSSION, editedDiscussion);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(editedDiscussion);
        assertEquals(expectedUniqueMeetingList, UniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasDifferentIdentity_success() {
        UniqueMeetingList.add(DISCUSSION);
        UniqueMeetingList.setMeeting(DISCUSSION, ROUNDTABLE);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(ROUNDTABLE);
        assertEquals(expectedUniqueMeetingList, UniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasNonUniqueIdentity_throwsDuplicateMeetingException() {
        UniqueMeetingList.add(DISCUSSION);
        UniqueMeetingList.add(ROUNDTABLE);
        assertThrows(DuplicateMeetingException.class, () -> UniqueMeetingList.setMeeting(DISCUSSION, ROUNDTABLE));
    }

    @Test
    public void remove_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueMeetingList.remove(null));
    }

    @Test
    public void remove_meetingDoesNotExist_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> UniqueMeetingList.remove(DISCUSSION));
    }

    @Test
    public void remove_existingMeeting_removesMeeting() {
        UniqueMeetingList.add(DISCUSSION);
        UniqueMeetingList.remove(DISCUSSION);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        assertEquals(expectedUniqueMeetingList, UniqueMeetingList);
    }

    @Test
    public void setMeetings_nullUniqueMeetingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueMeetingList.setMeetings((UniqueMeetingList) null));
    }

    @Test
    public void setMeetings_UniqueMeetingList_replacesOwnListWithProvidedUniqueMeetingList() {
        UniqueMeetingList.add(DISCUSSION);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(ROUNDTABLE);
        UniqueMeetingList.setMeetings(expectedUniqueMeetingList);
        assertEquals(expectedUniqueMeetingList, UniqueMeetingList);
    }

    @Test
    public void setMeetings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueMeetingList.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_list_replacesOwnListWithProvidedList() {
        UniqueMeetingList.add(DISCUSSION);
        List<Meeting> meetingList = Collections.singletonList(ROUNDTABLE);
        UniqueMeetingList.setMeetings(meetingList);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(ROUNDTABLE);
        assertEquals(expectedUniqueMeetingList, UniqueMeetingList);
    }

    @Test
    public void setMeetings_listWithDuplicateMeetings_throwsDuplicateMeetingException() {
        List<Meeting> listWithDuplicateMeetings = Arrays.asList(DISCUSSION, DISCUSSION);
        assertThrows(DuplicateMeetingException.class, () -> UniqueMeetingList.setMeetings(listWithDuplicateMeetings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> UniqueMeetingList.asUnmodifiableObservableList().remove(0));
    }
}
