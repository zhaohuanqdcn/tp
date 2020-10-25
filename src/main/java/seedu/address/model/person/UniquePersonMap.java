package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonMap implements Iterable<Person> {

    private final ObservableMap<UUID, Person> internalMap = FXCollections.observableHashMap();
    private final ObservableMap<UUID, Person> internalUnmodifiableMap =
            FXCollections.unmodifiableObservableMap(internalMap);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
//        return internalList.values().stream().anyMatch(toCheck::isSamePerson);
        return internalMap.containsValue(toCheck);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalMap.putIfAbsent(toAdd.getUuid(), toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        if (!internalMap.containsValue(target)) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }
        internalMap.replace(target.getUuid(), target, editedPerson);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);

        internalMap.remove(toRemove.getUuid(), toRemove);
        if (!internalMap.containsValue(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonMap replacement) {
        requireNonNull(replacement);

        internalMap.clear();
        internalMap.putAll(replacement.internalMap);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(Map<UUID, Person> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(new ArrayList<>(persons.values()))) {
            throw new DuplicatePersonException();
        }

        internalMap.clear();
        internalMap.putAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableMap}.
     */
    public ObservableMap<UUID, Person> asUnmodifiableObservableMap() {
        return internalUnmodifiableMap;
    }

    @Override
    public Iterator<Person> iterator() {
        return internalMap.values().iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonMap // instanceof handles nulls
                && internalMap.equals(((UniquePersonMap) other).internalMap));
    }

    @Override
    public int hashCode() {
        return internalMap.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Person> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSamePerson(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
