package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_PARTICIPANTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UniqueMeetingList.Pair;

public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "add_meeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to your schedule. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DATETIME + "DATETIME "
            + PREFIX_DURATION + "DURATION "
            + PREFIX_LOCATION + "LOCATION "
            + "[" + PREFIX_ADD_PARTICIPANTS + "PARTICIPANTS]..."
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "v1.3 discussion "
            + PREFIX_DATETIME + "31/12/20 1400 "
            + PREFIX_DURATION + "1 30 "
            + PREFIX_LOCATION + "Cool spot "
            + PREFIX_RECURRENCE + "weekly";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s \n "
            + "Add participants by finding their name, and key in their index on the list";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the schedule";
    public static final String MESSAGE_CONFLICT_MEETING =
            "This meeting conflicts with an existing meeting in the list :\n";

    private final Meeting toAdd;

    /**
     * Creates an AddMeetingCommand to add the specified {@code Meeting}
     */
    public AddMeetingCommand(Meeting meeting) {
        requireNonNull(meeting);
        toAdd = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasMeeting(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        // check if all recurrence meetings do not conflict with current schedule, then proceed to add them
        for (Meeting meeting : toAdd.getRecurrencesAsList()) {
            Pair<Boolean, Optional<Meeting>> conflictCheckResult = model.hasConflict(meeting);
            if (conflictCheckResult.getValueOne()) {
                throw new CommandException(MESSAGE_CONFLICT_MEETING + conflictCheckResult.getValueTwo().get());
            }
        }

        for (Meeting meeting : toAdd.getRecurrencesAsList()) {
            model.addMeeting(meeting);
        }

        model.sortMeeting();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMeetingCommand // instanceof handles nulls
                && toAdd.equals(((AddMeetingCommand) other).toAdd));
    }
}
