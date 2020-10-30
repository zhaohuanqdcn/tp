package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.meeting.Meeting;


/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class IcsAddressBookStorage implements AddressBookStorage {
    private static final Logger logger = LogsCenter.getLogger(IcsAddressBookStorage.class);

    private String version = "VERSION:2.0\r\n";
    private String prodid = "PRODID:Recretary\r\n";
    private String calBegin = "BEGIN:VCALENDAR\r\n";
    private String calEnd = "END:VCALENDAR\r\n";

    private Path filePath;

    public IcsAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    //not done
    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        FileUtil.writeToFile(filePath, convertToIcs(addressBook.getMeetingList()));
    }

    /**
     * Converts the given list of meetings to a String following ICS format.
     */
    public String convertToIcs(ObservableList<Meeting> meetings) {
        IcsFormatMeeting meetingFormatter = new IcsFormatMeeting();
        StringBuilder builder = new StringBuilder();
        builder.append(calBegin);
        builder.append(version);
        builder.append(prodid);
        for (Meeting m: meetings) {
            builder.append(meetingFormatter.toString(m));
        }
        builder.append(calEnd);
        return builder.toString();
    }

}
