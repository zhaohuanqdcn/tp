package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ExportMeetingCommand extends Command {

    public static final String COMMAND_WORD = "export_meeting";
    public static final String MESSAGE_SUCCESS = "Meetings have been exported as .ics!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
