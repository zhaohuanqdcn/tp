package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetings.DISCUSSION;
import static seedu.address.testutil.TypicalMeetings.ROUNDTABLE;
import static seedu.address.testutil.TypicalMeetings.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.DataContainsKeywordsPredicate;
import seedu.address.model.memento.History;
import seedu.address.model.memento.StateManager;

/**
 * Contains integration tests (interaction with the Model) for {@code FindMeetingCommand}.
 */
public class FindMeetingCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            new StateManager(), new History());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            new StateManager(), new History());

    @Test
    public void equals() {
        DataContainsKeywordsPredicate firstPredicate =
                new DataContainsKeywordsPredicate(Collections.singletonList("first"));
        DataContainsKeywordsPredicate secondPredicate =
                new DataContainsKeywordsPredicate(Collections.singletonList("second"));

        FindMeetingCommand findFirstCommand = new FindMeetingCommand(firstPredicate);
        FindMeetingCommand findSecondCommand = new FindMeetingCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindMeetingCommand findFirstCommandCopy = new FindMeetingCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noMeetingFound() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 0);
        DataContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindMeetingCommand command = new FindMeetingCommand(predicate);
        expectedModel.updateFilteredMeetingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multipleMeetingsFound() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 2);
        DataContainsKeywordsPredicate predicate = preparePredicate("2012 Alice");
        FindMeetingCommand command = new FindMeetingCommand(predicate);
        expectedModel.updateFilteredMeetingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DISCUSSION, ROUNDTABLE), model.getFilteredMeetingList());
    }

    /**
     * Parses {@code userInput} into a {@code DataContainsKeywordsPredicate}.
     */
    private DataContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DataContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
