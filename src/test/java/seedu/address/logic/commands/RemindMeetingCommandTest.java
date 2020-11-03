package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalMeetings.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.MeetingWithinHoursPredicate;
import seedu.address.model.memento.History;
import seedu.address.model.memento.StateManager;

public class RemindMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new StateManager(), new History());

    @Test
    public void equals() {
        MeetingWithinHoursPredicate predicateOne = new MeetingWithinHoursPredicate(10);
        MeetingWithinHoursPredicate predicateTwo = new MeetingWithinHoursPredicate(20);

        RemindMeetingCommand remindCommandOne = new RemindMeetingCommand(predicateOne);
        RemindMeetingCommand remindCommandTwo = new RemindMeetingCommand(predicateTwo);

        // same object -> returns true
        assertTrue(remindCommandOne.equals(remindCommandOne));

        // same values -> returns true
        RemindMeetingCommand remindCommandOneCopy = new RemindMeetingCommand(predicateOne);
        assertTrue(remindCommandOne.equals(remindCommandOneCopy));

        // different types -> returns false
        assertFalse(remindCommandOne.equals(1));

        // null -> returns false
        assertFalse(remindCommandOne.equals(null));

        // different meeting -> returns false
        assertFalse(remindCommandOne.equals(remindCommandTwo));
    }

}
