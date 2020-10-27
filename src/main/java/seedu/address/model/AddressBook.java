package seedu.address.model;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.UniquePersonMap;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList personList;
    private final UniquePersonMap persons;
    private final UniqueMeetingList meetings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        personList = new UniquePersonList();
        persons = new UniquePersonMap();
        meetings = new UniqueMeetingList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(Map<UUID, Person> persons) {
        this.persons.setPersons(persons);
        this.personList.setPersons(new ArrayList<>(persons.values()));
    }

    /**
     * Replaces the contents of the meeting list with {@code meetings}.
     * {@code meetings} must not contain duplicate meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        this.meetings.setMeetings(meetings);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonMap());
        setMeetings(newData.getMeetingList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
        personList.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
        personList.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        personList.remove(key);
    }

    //// meeting-level operations

    /**
     * Returns true if a meeting with the same identity as {@code meeting} exists in the address book.
     */
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetings.contains(meeting);
    }

    /**
     * Adds a meeting to the address book.
     * The meeting must not already exist in the address book.
     */
    public void addMeeting(Meeting m) {
        assert !isNull(m);
        meetings.add(m);
    }

    /**
     * Sorts all the existing meetings in the address book according to date and time.
     */
    public void sortMeeting() {
        meetings.sort();
    }

    /**
     * Replaces the given meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the address book.
     * The meeting identity of {@code editedMeeting} must not be the same as
     * another existing meeting in the address book.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireNonNull(editedMeeting);

        meetings.setMeeting(target, editedMeeting);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeMeeting(Meeting key) {
        assert !isNull(key);
        meetings.remove(key);
    }

    /**
     * Removes all recurrences of {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeRecurringMeetings(Meeting key) {
        assert !isNull(key);
        FilteredList<Meeting> toRemove = meetings.getRecurringMeetings(key);
        List<Meeting> toRemoveObjects = new ArrayList<>(toRemove);
        toRemoveObjects.forEach(meetings::remove);
    }

    //// util methods

    @Override
    public String toString() {
        return super.toString() + persons.asUnmodifiableObservableList().size() + " persons\n"
                + meetings.asUnmodifiableObservableList().size() + " meetings";
        // TODO: refine later
    }

    public ObservableMap<UUID, Person> getPersonMap() {
        return persons.asUnmodifiableObservableMap();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return personList.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Meeting> getMeetingList() {
        return meetings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && personList.equals(((AddressBook) other).personList)
                && meetings.equals(((AddressBook) other).meetings));
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, meetings);
    }
}
