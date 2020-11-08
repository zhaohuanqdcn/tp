package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_USER_PREF;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.EditUserPrefCommand.MESSAGE_EDIT_USER_PREFERENCE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.memento.History;
import seedu.address.model.memento.StateManager;
import seedu.address.testutil.EditUserPrefDescriptorBuilder;
import seedu.address.testutil.TypicalMeetings;

public class EditUserPrefCommandTest {

    private Model model = new ModelManager(TypicalMeetings.getTypicalAddressBook(), new UserPrefs(),
            new StateManager(), new History());

    @Test
    public void equals() {
        final EditUserPrefCommand standardCommand = new EditUserPrefCommand(DESC_USER_PREF);
        EditUserPrefCommand.EditUserPrefDescriptor sameDescriptor = new EditUserPrefDescriptorBuilder(DESC_USER_PREF)
                .build();
        EditUserPrefCommand.EditUserPrefDescriptor differentDescriptor = new EditUserPrefDescriptorBuilder()
                .withInterval(0).build();
        EditUserPrefCommand sameCommand = new EditUserPrefCommand(sameDescriptor);
        EditUserPrefCommand diffCommand = new EditUserPrefCommand(differentDescriptor);

        // same values -> returns true
        assertTrue(standardCommand.equals(sameCommand));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different value -> returns false
        assertFalse(standardCommand.equals(diffCommand));
    }

    @Test
    public void execute_validIntervalUpdateUserPref_success() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setIntervalBetweenMeetings(10);
        Model expectedModel = new ModelManager(TypicalMeetings.getTypicalAddressBook(),
                userPrefs, new StateManager(), new History());
        EditUserPrefCommand command = new EditUserPrefCommand(DESC_USER_PREF);
        String expectedMessage = MESSAGE_EDIT_USER_PREFERENCE_SUCCESS + userPrefs.toString();

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

    }


}
