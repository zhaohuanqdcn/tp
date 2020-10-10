package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

public class AddParticipantCommand extends Command {

    public static final String COMMAND_WORD = "add_part";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a participant to your meeting. \n"
            + "Parameters: KEYWORD, INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John"
            + PREFIX_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "New participant added to meeting: %1$s";
    public static final String MESSAGE_NO_MEETING = "There is no meeting to add participants to";

    private final Index targetIndex;
    private final NameContainsKeywordsPredicate predicate;

    /**
     * Creates an AddContactCommand to add the specified {@code Person}
     */
    public AddParticipantCommand(NameContainsKeywordsPredicate predicate, Index targetIndex) {
        this.targetIndex = targetIndex;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        List<Person> filteredPersonList = model.getFilteredPersonList();

        Person personToAdd = filteredPersonList.get(targetIndex.getZeroBased());

        List<Meeting> filteredMeetingList = model.getFilteredMeetingList();

        if (filteredMeetingList.size() == 0) {
            throw new CommandException(MESSAGE_NO_MEETING);
        }

        Meeting toAdd = filteredMeetingList.get(filteredMeetingList.size() - 1);
        model.deleteMeeting(toAdd);
        toAdd.addParticipant(personToAdd);

        model.addMeeting(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddParticipantCommand // instanceof handles nulls
                && predicate.equals(((AddParticipantCommand) other).predicate)
                && targetIndex.equals(((AddParticipantCommand) other).targetIndex));
    }

}