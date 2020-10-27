package seedu.address.logic;

import java.nio.file.Path;
import java.util.UUID;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.memento.History;
import seedu.address.model.memento.StateManager;
import seedu.address.model.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the map of persons */
    ObservableMap<UUID, Person> getPersonMap();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of meetings */
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the state manager of the current app.
     */
    StateManager getStateManager();

    /**
     * Returns the history of states of the current app.
     */
    History getHistory();
}
