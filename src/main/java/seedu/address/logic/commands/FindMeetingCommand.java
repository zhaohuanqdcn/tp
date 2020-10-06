package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.meeting.DataContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all meetings in address book whose string data contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindMeetingCommand extends Command {

    public static final String COMMAND_WORD = "find_meeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meetings whose string data contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " nus 2020 meeting";

    private final DataContainsKeywordsPredicate predicate;

    public FindMeetingCommand(DataContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindMeetingCommand // instanceof handles nulls
                && predicate.equals(((FindMeetingCommand) other).predicate)); // state check
    }
}
