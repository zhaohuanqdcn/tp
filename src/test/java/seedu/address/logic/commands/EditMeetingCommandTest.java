package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DISCUSSION;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.memento.History;
import seedu.address.model.memento.StateManager;
import seedu.address.testutil.EditMeetingDescriptorBuilder;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.TypicalMeetings;

class EditMeetingCommandTest {
    private Model model = new ModelManager(TypicalMeetings.getTypicalAddressBook(), new UserPrefs(),
            new StateManager(), new History());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Meeting editedMeeting = new MeetingBuilder().build();
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_MEETING, descriptor);


        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                new StateManager(), new History());

        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);
        // model is sorted due to the underlying add_meeting command, therefore expectedModel also needs to be sorted
        expectedModel.sortMeeting();
        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final EditMeetingCommand standardCommand = new EditMeetingCommand(INDEX_FIRST_MEETING, DESC_DISCUSSION);

        // same values -> returns true
        EditMeetingCommand.EditMeetingDescriptor copyDescriptor =
                new EditMeetingCommand.EditMeetingDescriptor(DESC_DISCUSSION);
        EditMeetingCommand commandWithSameValues = new EditMeetingCommand(INDEX_FIRST_MEETING, copyDescriptor);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditMeetingCommand(INDEX_SECOND_MEETING, DESC_DISCUSSION)));

    }
}
