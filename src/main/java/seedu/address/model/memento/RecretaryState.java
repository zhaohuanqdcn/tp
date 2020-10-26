package seedu.address.model.memento;

import seedu.address.model.ReadOnlyAddressBook;

public class RecretaryState {

    private final ReadOnlyAddressBook addressBook;

    public RecretaryState(ReadOnlyAddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }
}
