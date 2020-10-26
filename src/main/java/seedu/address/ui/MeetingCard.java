package seedu.address.ui;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Meeting}.
 */
public class MeetingCard extends UiPart<Region> {

    private static final String FXML = "MeetingListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Meeting meeting;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label datetime;
    @FXML
    private Label duration;
    @FXML
    private Label loc;
    @FXML
    private FlowPane participants;

    /**
     * Creates a {@code MeetingCard} with the given {@code Meeting} and index to display.
     */
    public MeetingCard(Logic logic, Meeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        title.setText(meeting.getTitle().value);
        datetime.setText(meeting.getDateTime().value.toString());
        duration.setText(meeting.getDuration().toString());
        loc.setText(meeting.getLocation().toString());

        meeting.getParticipants().stream().forEach(partcipant ->
                participants.getChildren().add(
                        new Label(logic.getPersonMap().get(partcipant).getName().toString())));

    }

    public Set<Person> getPersonParticipants(Set<UUID> uuids, ObservableMap<UUID, Person> persons) {
        Set<Person> participants = new HashSet<>();
        for (UUID uuid : uuids) {
            participants.add(persons.get(uuid));
        }
        return participants;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingCard)) {
            return false;
        }

        // state check
        MeetingCard card = (MeetingCard) other;
        return id.getText().equals(card.id.getText())
                && meeting.equals(card.meeting);
    }
}
