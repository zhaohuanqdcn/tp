package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.meeting.MeetingWithinHoursPredicate;

/**
 * Finds and lists all meetings whose time is within certain hours.
 */
public class RemindMeetingCommand extends Command {

    public static final String COMMAND_WORD = "remind_meeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all meetings that are "
            + "within certain hours relative to the time on the local machine "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: HOURS (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 72";

    private final MeetingWithinHoursPredicate predicate;

    public RemindMeetingCommand(MeetingWithinHoursPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMeetingList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, model.getFilteredMeetingList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemindMeetingCommand // instanceof handles nulls
                && predicate.equals(((RemindMeetingCommand) other).predicate)); // state check
    }
}
