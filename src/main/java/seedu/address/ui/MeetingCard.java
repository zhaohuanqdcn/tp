package seedu.address.ui;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.meeting.Duration;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Meeting}.
 */
public class MeetingCard extends UiPart<Region> {

    private static final String FXML = "MeetingListCard.fxml";
    private final Logger logger = LogsCenter.getLogger(MeetingCard.class);


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Meeting meeting;

    @FXML
    private HBox meetingCard;
    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label duration;
    @FXML
    private VBox timelineDetails;
    @FXML
    private Label loc;
    @FXML
    private FlowPane participants;
    @FXML
    private Rectangle meetingBar;
    @FXML
    private ImageView locationIcon;
    @FXML
    private ImageView withIcon;
    @FXML
    private HBox datePlaceholder;
    @FXML
    private HBox currentTimeBarPlaceholder;

    /**
     * Creates a {@code MeetingCard} with the given {@code Meeting}, index to display,
     * whether it is the first meeting of the day and index of next earliest meeting.
     */
    public MeetingCard(ObservableMap<UUID, Person> personMap, Meeting meeting, int displayedIndex,
                       boolean isFirstInDay, int indexOfNextEarliestMeeting) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + "");
        title.setText(meeting.getTitle().value);

        startTime.setText(meeting.getDateTime().getStartTime());

        endTime.setText(meeting.getDateTime().getEndTime(meeting.getDuration()));

        duration.setText(meeting.getDuration().toString());

        loc.setText(meeting.getLocation().toString());

        meeting.getParticipants().stream().forEach(partcipant ->
                participants.getChildren().add(
                        new Label(personMap.get(partcipant).getName().toString())));

    
        // sizing according to duration
        setDynamicSize(meeting.getDuration());

        logger.info(timelineDetails.getWidth() + "");

        locationIcon.setImage(new Image(getClass().getResourceAsStream("/images/location.png")));
        withIcon.setImage(new Image(getClass().getResourceAsStream("/images/with.png")));

        // for optional day headings
        if (isFirstInDay) {
            Label dateLabel = new Label(meeting.getDateTime().getDate());
            dateLabel.setStyle("-fx-text-fill: #CCB9B9");
            datePlaceholder.getChildren().add(dateLabel);
        }

        // for optional green bar
        if (indexOfNextEarliestMeeting == displayedIndex) {
            Rectangle greenBar = new Rectangle();
            greenBar.setArcHeight(16);
            greenBar.setArcWidth(16);
            greenBar.setHeight(16);
            greenBar.setWidth(60);
            greenBar.setStyle("-fx-fill: #93E244;");
            currentTimeBarPlaceholder.setPadding(new Insets(0, 0, 8, 0));
            currentTimeBarPlaceholder.getChildren().add(greenBar);
        }
    }
  
    public Set<Person> getPersonParticipants(Set<UUID> uuids, ObservableMap<UUID, Person> persons) {
        Set<Person> participants = new HashSet<>();
        for (UUID uuid : uuids) {
            participants.add(persons.get(uuid));
        }
        return participants;
    }

    private void setDynamicSize(Duration duration) {
        double length = duration.hours * 60 + duration.minutes;

        meetingBar.heightProperty().setValue(length);
        length /= 10;
        timelineDetails.setSpacing(length);
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
