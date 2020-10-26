package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetings.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.memento.History;
import seedu.address.model.memento.StateManager;

public class ClearMeetingCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearMeetingCommand(), model, ClearMeetingCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new StateManager(), new History());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new StateManager(), new History());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearMeetingCommand(), model, ClearMeetingCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
