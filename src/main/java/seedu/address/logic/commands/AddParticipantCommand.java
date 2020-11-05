package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

public class AddParticipantCommand extends Command {

    public static final String COMMAND_WORD = "addpart";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a participant to your meeting. \n"
            + "Parameters: "
            + PREFIX_CONTACT_INDEX + "CONTACT_INDEX MEETING_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CONTACT_INDEX + "1"
            + PREFIX_MEETING_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "New participant added to meeting: %1$s";
    public static final String MESSAGE_NO_MEETING = "This meeting does not exist!";
    public static final String MESSAGE_NO_CONTACT = "This contact does not exist!";
    public static final String MESSAGE_DUPLICATE_PARTICIPANT = "This participant already exists in the schedule!";

    private final Index participantIndex;
    private final Index meetingIndex;

    /**
     * Creates an AddContactCommand to add the specified {@code Person}
     */
    public AddParticipantCommand(Index participantIndex, Index meetingIndex) {
        this.participantIndex = participantIndex;
        this.meetingIndex = meetingIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> filteredPersonList = model.getFilteredPersonList();

        if (participantIndex.getZeroBased() >= filteredPersonList.size()) {
            throw new CommandException(MESSAGE_NO_CONTACT);
        }

        Person personToAdd = filteredPersonList.get(participantIndex.getZeroBased());

        List<Meeting> filteredMeetingList = model.getFilteredMeetingList();

        if (meetingIndex.getZeroBased() >= filteredMeetingList.size()) {
            throw new CommandException(MESSAGE_NO_MEETING);
        }

        Meeting toAdd = filteredMeetingList.get(meetingIndex.getZeroBased());

        if (toAdd.hasParticipant(personToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PARTICIPANT);
        }
        model.deleteMeeting(toAdd);
        toAdd.addParticipant(personToAdd);

        model.addMeeting(toAdd);
        model.sortMeeting();
        return new CommandResult(String.format(MESSAGE_SUCCESS, personToAdd.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddParticipantCommand // instanceof handles nulls
                && meetingIndex.equals(((AddParticipantCommand) other).meetingIndex)
                && participantIndex.equals(((AddParticipantCommand) other).participantIndex));
    }

}
