package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.memento.History;
import seedu.address.model.memento.StateManager;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class UndoCommandIntegrationTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            new StateManager(), new History());

    @Test
    void undo_singleCommand_previousState() throws CommandException {
        Person person = new PersonBuilder().build();
        new AddContactCommand(person).execute(model);
        // model.getHistory().push(new RecretaryState("add",
        //     new ArrayList<>(Collections.singletonList(person)), new ArrayList<>()));

        CommandResult result = new UndoCommand().execute(model);
        assertEquals(String.format(UndoCommand.MESSAGE_UNDO_SUCCESS, "add"), result.getFeedbackToUser());
    }

    @Test
    void undo_multipleCommands_undoNotInState() throws CommandException {
        Person person = new PersonBuilder().build();
        new AddContactCommand(person).execute(model);
        // model.getHistory().push(new RecretaryState("add",
        // new ArrayList<>(Collections.singletonList(person)), new ArrayList<>()));

        CommandResult result = new UndoCommand().execute(model);
        assertEquals(String.format(UndoCommand.MESSAGE_UNDO_SUCCESS, "add"), result.getFeedbackToUser());

        person = new PersonBuilder().withEmail("abc@gmail.com").withName("Adi").build();
        new AddContactCommand(person).execute(model);
        // model.getHistory().push(new RecretaryState("add2",
        // new ArrayList<>(Collections.singletonList(person)), new ArrayList<>()));

        result = new UndoCommand().execute(model);
        assertEquals(String.format(UndoCommand.MESSAGE_UNDO_SUCCESS, "add2"), result.getFeedbackToUser());

    }
}
