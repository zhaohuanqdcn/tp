package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.memento.RecretaryState;

/**
 * Undoes a given number of commands which is 1 by default.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undoes a number of commands given in the INDEX.\n"
            + "Parameters: [INDEX] (must be a positive integer, optional)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNDO_SUCCESS = "Undid the following commands: %1$s";

    private final Index targetNumOfCommands;

    /**
     * Constructor for a command to undo the previous command
     */
    public UndoCommand() {
        this.targetNumOfCommands = Index.fromOneBased(1);
    }
    /**
     * Constructor for a command to undo the previous n commands
     */
    public UndoCommand(Index index) {
        this.targetNumOfCommands = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getHistory().getSize() < 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_UNDO);
        }

        StringBuilder toPrint = new StringBuilder();

        for (int i = 0; i < targetNumOfCommands.getZeroBased(); i++) {
            toPrint.append(model.getHistory().pop().getCommand()).append(", ");
        }

        RecretaryState stateToRestore = model.getHistory().pop();
        toPrint.append(stateToRestore.getCommand());

        AddressBook ab = new AddressBook();
        ab.setPersons(stateToRestore.getPersonList());
        ab.setMeetings(stateToRestore.getMeetingList());
        model.setAddressBook(ab);

        return new CommandResult(String.format(MESSAGE_UNDO_SUCCESS, toPrint.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UndoCommand // instanceof handles nulls
                && targetNumOfCommands.equals(((UndoCommand) other).targetNumOfCommands)); // state check
    }
}
