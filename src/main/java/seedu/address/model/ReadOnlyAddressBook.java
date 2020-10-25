package seedu.address.model;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

import java.util.UUID;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();
    ObservableMap<UUID, Person> getPersonMap();

    /**
     * Returns an unmodifiable view of the meetings list.
     * This list will not contain any duplicate meetings.
     */
    ObservableList<Meeting> getMeetingList();

}
