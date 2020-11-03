package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRENCE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;

/**
 * Deletes a meeting identified using it's displayed index from the address book.
 */
public class DeleteMeetingCommand extends Command {

    public static final String COMMAND_WORD = "deletemeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the meeting (and possibly its recurrences) "
            + "identified by the index number used in the displayed meeting list.\n"
            + "Parameters: "
            + "INDEX "
            + "[" + PREFIX_RECURRENCE + "RECURRING] \n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_RECURRENCE + "true";

    public static final String MESSAGE_DELETE_MEETING_SUCCESS = "Deleted Meeting: %1$s";

    private final Index targetIndex;
    private final boolean deleteRecurrence;

    /**
     * Constructor for a command to delete a single instance
     */
    public DeleteMeetingCommand(Index targetIndex) {
        this.deleteRecurrence = false;
        this.targetIndex = targetIndex;
    }

    /**
     * Constructor for a command to delete all recurrences
     */
    public DeleteMeetingCommand(Index targetIndex, boolean deleteRecurrence) {
        this.deleteRecurrence = deleteRecurrence;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }
        Meeting meetingToDelete = lastShownList.get(targetIndex.getZeroBased());
        if (!deleteRecurrence) { // delete only one instance
            model.deleteMeeting(meetingToDelete);
        } else { // delete all recurrences
            model.deleteRecurringMeetings(meetingToDelete);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteMeetingCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteMeetingCommand) other).targetIndex)) // state check
                && deleteRecurrence == ((DeleteMeetingCommand) other).deleteRecurrence;
    }
}
