package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UniqueMeetingList.Pair;
import seedu.address.model.memento.History;
import seedu.address.model.memento.StateManager;
import seedu.address.model.person.Person;
import seedu.address.testutil.MeetingBuilder;

class AddMeetingCommandTest {

    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null, 1));
    }

    @Test
    public void execute_meetingWithoutRecurrence_addSuccessful() throws Exception {
        AddMeetingCommandTest.ModelStubAcceptingMeetingAdded modelStub =
                new AddMeetingCommandTest.ModelStubAcceptingMeetingAdded();
        Meeting validMeeting = new MeetingBuilder().build();

        CommandResult commandResult = new AddMeetingCommand(validMeeting, 1).execute(modelStub);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, validMeeting), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMeeting), modelStub.meetingsAdded);
    }

    @Test
    public void execute_meetingWithRecurrence_addSuccessful() throws Exception {
        AddMeetingCommandTest.ModelStubAcceptingMeetingAdded modelStub =
                new AddMeetingCommandTest.ModelStubAcceptingMeetingAdded();
        Meeting validMeeting = new MeetingBuilder().withRecurrence("daily").build();

        CommandResult commandResult = new AddMeetingCommand(validMeeting, 5).execute(modelStub);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, validMeeting), commandResult.getFeedbackToUser());
        assertEquals(validMeeting.getRecurrencesAsList(5), modelStub.meetingsAdded);
    }



    @Test
    void testEquals() {
        Meeting party = new MeetingBuilder().withTitle("party").build();
        Meeting presentation = new MeetingBuilder().withTitle("presentation").build();
        AddMeetingCommand addPartyCommand = new AddMeetingCommand(party, 5);
        AddMeetingCommand addPresentationCommand = new AddMeetingCommand(presentation, 5);

        // same object -> returns true
        assertTrue(addPartyCommand.equals(addPartyCommand));

        // same values -> returns true
        AddMeetingCommand addPresentationCommandCopy = new AddMeetingCommand(presentation, 5);
        assertTrue(addPresentationCommand.equals(addPresentationCommandCopy));

        // different types -> returns false
        assertFalse(addPartyCommand.equals(0));

        // null -> returns false
        assertFalse(addPartyCommand.equals(null));

        // different meeting -> returns false
        assertFalse(addPartyCommand.equals(addPresentationCommand));

        // different recurring number -> returns false
        AddMeetingCommand addPartyCommandCopy = new AddMeetingCommand(party, 1);
        assertFalse(addPartyCommand.equals(addPartyCommandCopy));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void reattachDependentMeetings(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void refreshApplication() {
            // do nothing
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortMeeting() {
            //do nothing because of stub
        }

        @Override
        public Pair hasConflict(Meeting meeting) {
            return new Pair(false, Optional.empty());
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Meeting getNextMeeting(long offset) {
            return null;
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeeting(Meeting target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRecurringMeetings(Meeting target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeeting(Meeting target, Meeting editedMeeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableMap<UUID, Person> getPersonMap() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Meeting> getFilteredMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getParticipant(UUID uuid) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public StateManager getStateManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public History getHistory() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithMeeting extends AddMeetingCommandTest.ModelStub {
        private final Meeting meeting;

        ModelStubWithMeeting(Meeting meeting) {
            requireNonNull(meeting);
            this.meeting = meeting;
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return this.meeting.isSameMeeting(meeting);
        }
    }

    /**
     * A Model stub that always accept the meeting being added.
     */
    private class ModelStubAcceptingMeetingAdded extends AddMeetingCommandTest.ModelStub {
        final ArrayList<Meeting> meetingsAdded = new ArrayList<>();

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return meetingsAdded.stream().anyMatch(meeting::isSameMeeting);
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            meetingsAdded.add(meeting);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
