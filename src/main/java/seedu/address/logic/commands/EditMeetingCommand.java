package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_PARTICIPANTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Duration;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Title;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing meeting in Recretary.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "edit_meeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_DURATION + "DURATION] "
            + "[" + PREFIX_DATETIME + "DATETIME] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_ADD_PARTICIPANTS + "PARTICIPANTS] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TITLE + "OP2 "
            + PREFIX_DURATION + "01 20";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book.";

    private final Index index;
    private final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editMeetingDescriptor details to edit the person with
     */
    public EditMeetingCommand(Index index, EditMeetingDescriptor editMeetingDescriptor) {
        requireNonNull(index);
        requireNonNull(editMeetingDescriptor);

        this.index = index;
        this.editMeetingDescriptor = new EditMeetingDescriptor(editMeetingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        this.editMeetingDescriptor.setModel(model);
        Meeting meetingToEdit = lastShownList.get(index.getZeroBased());
        Meeting editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor);

        if (!meetingToEdit.isSameMeeting(editedMeeting) && model.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.setMeeting(meetingToEdit, editedMeeting);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting));
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code editMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        Title updatedTitle = editMeetingDescriptor.getTitle().orElse(meetingToEdit.getTitle());
        DateTime updatedDateTime = editMeetingDescriptor.getDateTime().orElse(meetingToEdit.getDateTime());
        Duration updatedDuration = editMeetingDescriptor.getDuration().orElse(meetingToEdit.getDuration());
        Location updatedLocation = editMeetingDescriptor.getLocation().orElse(meetingToEdit.getLocation());
        Set<Person> updatedPersons = editMeetingDescriptor.getPersons().orElse(meetingToEdit.getParticipants());

        return new Meeting(updatedTitle, updatedDuration, updatedDateTime, updatedLocation, updatedPersons);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditContactCommand)) {
            return false;
        }

        // state check
        EditMeetingCommand e = (EditMeetingCommand) other;
        return index.equals(e.index)
                && editMeetingDescriptor.equals(e.editMeetingDescriptor);
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the
     * corresponding field value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private Title title;
        private DateTime dateTime;
        private Duration duration;
        private Location location;
        private Set<Person> persons;
        private Model model;

        public EditMeetingDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditMeetingDescriptor(EditMeetingCommand.EditMeetingDescriptor toCopy) {
            setTitle(toCopy.title);
            setDateTime(toCopy.dateTime);
            setDuration(toCopy.duration);
            setLocation(toCopy.location);
            setPersons(toCopy.persons);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, dateTime, duration, location, persons);
        }

        public void setModel(Model model) {
            this.model = model;
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setDateTime(DateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<DateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public Optional<Duration> getDuration() {
            return Optional.ofNullable(duration);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setPersons(Set<Person> persons) {
            this.persons = (persons != null) ? new HashSet<>(persons) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Person>> getPersons() {
            return (persons != null) ? Optional.of(Collections.unmodifiableSet(persons)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMeetingDescriptor)) {
                return false;
            }

            // state check
            EditMeetingDescriptor e = (EditMeetingDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDateTime().equals(e.getDateTime())
                    && getDuration().equals(e.getDuration())
                    && getLocation().equals(e.getLocation())
                    && getPersons().equals(e.getPersons());
        }
    }
}
