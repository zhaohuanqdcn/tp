package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearContactCommand extends Command {

    public static final String COMMAND_WORD = "clearcontact";
    public static final String MESSAGE_SUCCESS = "Contacts have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        AddressBook ab = new AddressBook();
        ab.setMeetings(model.getFilteredMeetingList());
        model.setAddressBook(ab);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
