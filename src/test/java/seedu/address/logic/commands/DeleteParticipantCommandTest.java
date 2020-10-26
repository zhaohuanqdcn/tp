package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalMeetings.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.memento.History;
import seedu.address.model.memento.StateManager;

class DeleteParticipantCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new StateManager(), new History());

    @Test
    public void execute_validIndex_success() {
        Meeting meeting = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        DeleteParticipantCommand deleteParticipantCommand =
                new DeleteParticipantCommand(INDEX_FIRST_PERSON, INDEX_FIRST_MEETING);

        String expectedMessage = String.format(DeleteParticipantCommand.MESSAGE_SUCCESS, meeting);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new StateManager(), new History());

        expectedModel.deleteMeeting(meeting);
        meeting.delParticipant(INDEX_FIRST_PERSON);
        expectedModel.addMeeting(meeting);

        assertCommandSuccess(deleteParticipantCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteParticipantCommand deleteFirstCommand =
                new DeleteParticipantCommand(INDEX_FIRST_PERSON, INDEX_FIRST_MEETING);
        DeleteParticipantCommand deleteSecondCommand =
                new DeleteParticipantCommand(INDEX_SECOND_PERSON, INDEX_SECOND_MEETING);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteParticipantCommand deleteFirstCommandCopy =
                new DeleteParticipantCommand(INDEX_FIRST_PERSON, INDEX_FIRST_MEETING);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different meeting -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
