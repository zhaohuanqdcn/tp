package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_ROUNDTABLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARTICIPANT_ALICE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.CONFLICT_MEETING_EIGHT;
import static seedu.address.testutil.TypicalMeetings.CONFLICT_MEETING_ELEVEN;
import static seedu.address.testutil.TypicalMeetings.CONFLICT_MEETING_FIFTEEN;
import static seedu.address.testutil.TypicalMeetings.CONFLICT_MEETING_FIVE;
import static seedu.address.testutil.TypicalMeetings.CONFLICT_MEETING_FOUR;
import static seedu.address.testutil.TypicalMeetings.CONFLICT_MEETING_FOURTEEN;
import static seedu.address.testutil.TypicalMeetings.CONFLICT_MEETING_NINE;
import static seedu.address.testutil.TypicalMeetings.CONFLICT_MEETING_ONE;
import static seedu.address.testutil.TypicalMeetings.CONFLICT_MEETING_SEVEN;
import static seedu.address.testutil.TypicalMeetings.CONFLICT_MEETING_SIX;
import static seedu.address.testutil.TypicalMeetings.CONFLICT_MEETING_TEN;
import static seedu.address.testutil.TypicalMeetings.CONFLICT_MEETING_THIRTEEN;
import static seedu.address.testutil.TypicalMeetings.CONFLICT_MEETING_THREE;
import static seedu.address.testutil.TypicalMeetings.CONFLICT_MEETING_TWELVE;
import static seedu.address.testutil.TypicalMeetings.CONFLICT_MEETING_TWO;
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

    private final UniqueMeetingList uniqueMeetingList = new UniqueMeetingList();

    @Test
    public void contains_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.contains(null));
    }

    @Test
    public void contains_meetingNotInList_returnsFalse() {
        assertFalse(uniqueMeetingList.contains(DISCUSSION));
    }

    @Test
    public void contains_meetingInList_returnsTrue() {
        uniqueMeetingList.add(DISCUSSION);
        assertTrue(uniqueMeetingList.contains(DISCUSSION));
    }

    @Test
    public void contains_meetingWithSameIdentityFieldsInList_returnsTrue() {
        uniqueMeetingList.add(DISCUSSION);
        Meeting editedDiscussion = new MeetingBuilder(DISCUSSION).withLocation(VALID_LOCATION_ROUNDTABLE)
                .withParticipants(VALID_PARTICIPANT_ALICE.getUuid()).build();
        assertTrue(uniqueMeetingList.contains(editedDiscussion));
    }

    @Test
    public void add_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.add(null));
    }

    @Test
    public void add_duplicateMeeting_throwsDuplicateMeetingException() {
        uniqueMeetingList.add(DISCUSSION);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.add(DISCUSSION));
    }

    @Test
    public void setMeeting_nullTargetMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(null, DISCUSSION));
    }

    @Test
    public void setMeeting_nullEditedMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(DISCUSSION, null));
    }

    @Test
    public void setMeeting_targetMeetingNotInList_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.setMeeting(DISCUSSION, DISCUSSION));
    }

    @Test
    public void setMeeting_editedMeetingIsSameMeeting_success() {
        uniqueMeetingList.add(DISCUSSION);
        uniqueMeetingList.setMeeting(DISCUSSION, DISCUSSION);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(DISCUSSION);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasSameIdentity_success() {
        uniqueMeetingList.add(DISCUSSION);
        Meeting editedDiscussion = new MeetingBuilder(DISCUSSION).withLocation(VALID_LOCATION_ROUNDTABLE)
                .withParticipants(VALID_PARTICIPANT_ALICE.getUuid()).build();
        uniqueMeetingList.setMeeting(DISCUSSION, editedDiscussion);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(editedDiscussion);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasDifferentIdentity_success() {
        uniqueMeetingList.add(DISCUSSION);
        uniqueMeetingList.setMeeting(DISCUSSION, ROUNDTABLE);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(ROUNDTABLE);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasNonUniqueIdentity_throwsDuplicateMeetingException() {
        uniqueMeetingList.add(DISCUSSION);
        uniqueMeetingList.add(ROUNDTABLE);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.setMeeting(DISCUSSION, ROUNDTABLE));
    }

    @Test
    public void sortMeeting_addMeetingDifferentOrderHasEffect_success() {
        uniqueMeetingList.add(DISCUSSION);
        uniqueMeetingList.add(ROUNDTABLE);
        UniqueMeetingList expectedMeetingList = new UniqueMeetingList();
        expectedMeetingList.add(ROUNDTABLE);
        expectedMeetingList.add(DISCUSSION);
        assertFalse(uniqueMeetingList.equals(expectedMeetingList));
    }

    @Test
    public void sortMeeting_sortMeetingAfterAdd_success() {
        uniqueMeetingList.add(DISCUSSION);
        uniqueMeetingList.add(ROUNDTABLE);
        UniqueMeetingList expectedMeetingList = new UniqueMeetingList();
        expectedMeetingList.add(ROUNDTABLE);
        expectedMeetingList.add(DISCUSSION);
        uniqueMeetingList.sort();
        expectedMeetingList.sort();
        assertEquals(uniqueMeetingList, expectedMeetingList);
    }

    @Test
    public void checkConflict_meetingConflictBoundaryValueZeroInterval_failure() {
        uniqueMeetingList.add(CONFLICT_MEETING_ONE);
        assertTrue(uniqueMeetingList.checkConflict(CONFLICT_MEETING_TWO, 0).getLeftValue());
        assertFalse(uniqueMeetingList.checkConflict(CONFLICT_MEETING_THREE, 0).getLeftValue());
    }

    @Test
    public void checkConflict_meetingConflictBoundaryValueWithInterval_failure() {
        uniqueMeetingList.add(CONFLICT_MEETING_ONE);
        assertTrue(uniqueMeetingList.checkConflict(CONFLICT_MEETING_FOUR, 10).getLeftValue());
        assertFalse(uniqueMeetingList.checkConflict(CONFLICT_MEETING_FIVE, 10).getLeftValue());
    }

    @Test
    public void checkConflict_meetingConflictSameMeeting_failure() {
        uniqueMeetingList.add(CONFLICT_MEETING_ONE);
        assertTrue(uniqueMeetingList.checkConflict(CONFLICT_MEETING_ONE, 0).getLeftValue());
    }

    @Test
    public void checkConflict_meetingConflictBeforeBoundaryNoInterval_failure() {
        uniqueMeetingList.add(CONFLICT_MEETING_ONE);
        assertTrue(uniqueMeetingList.checkConflict(CONFLICT_MEETING_SIX, 0).getLeftValue());
        assertFalse(uniqueMeetingList.checkConflict(CONFLICT_MEETING_SEVEN, 0).getLeftValue());
        assertFalse(uniqueMeetingList.checkConflict(CONFLICT_MEETING_EIGHT, 0).getLeftValue());
    }

    @Test
    public void checkConflict_meetingConflictBeforeBoundaryWithInterval_failure() {
        uniqueMeetingList.add(CONFLICT_MEETING_ONE);
        assertTrue(uniqueMeetingList.checkConflict(CONFLICT_MEETING_NINE, 10).getLeftValue());
        assertFalse(uniqueMeetingList.checkConflict(CONFLICT_MEETING_TEN, 10).getLeftValue());
        assertFalse(uniqueMeetingList.checkConflict(CONFLICT_MEETING_ELEVEN, 10).getLeftValue());
    }

    @Test
    public void checkConflict_meetingNoConflict_success() {
        uniqueMeetingList.add(CONFLICT_MEETING_ONE);
        assertFalse(uniqueMeetingList.checkConflict(CONFLICT_MEETING_TWELVE, 10).getLeftValue());
        assertFalse(uniqueMeetingList.checkConflict(CONFLICT_MEETING_THIRTEEN, 10).getLeftValue());
        assertFalse(uniqueMeetingList.checkConflict(CONFLICT_MEETING_FOURTEEN, 30).getLeftValue());
        assertFalse(uniqueMeetingList.checkConflict(CONFLICT_MEETING_FIFTEEN, 15).getLeftValue());
    }

    @Test
    public void remove_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.remove(null));
    }

    @Test
    public void remove_meetingDoesNotExist_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.remove(DISCUSSION));
    }

    @Test
    public void remove_existingMeeting_removesMeeting() {
        uniqueMeetingList.add(DISCUSSION);
        uniqueMeetingList.remove(DISCUSSION);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nullUniqueMeetingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((UniqueMeetingList) null));
    }

    @Test
    public void setMeetings_uniqueMeetingList_replacesOwnListWithProvidedUniqueMeetingList() {
        uniqueMeetingList.add(DISCUSSION);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(ROUNDTABLE);
        uniqueMeetingList.setMeetings(expectedUniqueMeetingList);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_list_replacesOwnListWithProvidedList() {
        uniqueMeetingList.add(DISCUSSION);
        List<Meeting> meetingList = Collections.singletonList(ROUNDTABLE);
        uniqueMeetingList.setMeetings(meetingList);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(ROUNDTABLE);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_listWithDuplicateMeetings_throwsDuplicateMeetingException() {
        List<Meeting> listWithDuplicateMeetings = Arrays.asList(DISCUSSION, DISCUSSION);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.setMeetings(listWithDuplicateMeetings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueMeetingList.asUnmodifiableObservableList().remove(0));
    }
}
