package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;

public class DeleteParticipantCommand extends Command {

    public static final String COMMAND_WORD = "delete_part";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a participant from your meeting. \n"
            + "Parameters: CONTACT_INDEX MEETING_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CONTACT_INDEX + "1"
            + PREFIX_MEETING_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "Participant is deleted from meeting: %1$s";
    public static final String MESSAGE_NO_MEETING = "This meeting does not exist!";
    public static final String MESSAGE_NO_PARTICIPANT = "There is no such participant in the meeting!";

    private final Index participantIndex;
    private final Index meetingIndex;

    /**
     * Creates an AddContactCommand to add the specified {@code Person}
     */
    public DeleteParticipantCommand (Index participantIndex, Index meetingIndex) {
        this.participantIndex = participantIndex;
        this.meetingIndex = meetingIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Meeting> filteredMeetingList = model.getFilteredMeetingList();

        if (meetingIndex.getZeroBased() >= filteredMeetingList.size()) {
            throw new CommandException(MESSAGE_NO_MEETING);
        }

        Meeting meeting = filteredMeetingList.get(meetingIndex.getZeroBased());

        if (participantIndex.getZeroBased() >= meeting.getParticipants().size()) {
            throw new CommandException(MESSAGE_NO_PARTICIPANT);
        }

        model.deleteMeeting(meeting);
        meeting.delParticipant(participantIndex);
        model.addMeeting(meeting);
        model.sortMeeting();
        return new CommandResult(String.format(MESSAGE_SUCCESS, meeting));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteParticipantCommand // instanceof handles nulls
                && meetingIndex.equals(((DeleteParticipantCommand) other).meetingIndex)
                && participantIndex.equals(((DeleteParticipantCommand) other).participantIndex));
    }
}
