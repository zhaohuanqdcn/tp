package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearMeetingCommand extends Command {

    public static final String COMMAND_WORD = "clear_meeting";
    public static final String MESSAGE_SUCCESS = "Meetings has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        AddressBook ab = new AddressBook();
        ab.setPersons(model.getFilteredPersonList());
        model.setAddressBook(ab);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
