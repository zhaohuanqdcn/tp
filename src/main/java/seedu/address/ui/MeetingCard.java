package seedu.address.ui;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import seedu.address.model.meeting.Duration;
import seedu.address.model.meeting.Meeting;

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
     * Creates a {@code MeetingCard} with the given {@code Meeting}, index to display and timelineBar.
     */
    public MeetingCard(Meeting meeting, int displayedIndex,
                       boolean isFirstInDay, int indexOfNextMeeting) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + "");
        title.setText(meeting.getTitle().value);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");

        startTime.setText(timeFormatter.format(meeting.getDateTime().value));

        endTime.setText(timeFormatter.format(meeting
                .getDateTime()
                .value
                .plusHours(meeting.getDuration().hours)
                .plusMinutes(meeting.getDuration().minutes)));

        duration.setText(meeting.getDuration().toString());

        loc.setText(meeting.getLocation().toString());

        // sizing according to duration
        setDynamicSize(meeting.getDuration());

        meeting.getParticipants()
                .forEach(participant -> participants.getChildren().add(new Label(participant.getName().toString())));

        locationIcon.setImage(new Image(getClass().getResourceAsStream("/images/location.png")));
        withIcon.setImage(new Image(getClass().getResourceAsStream("/images/with.png")));

        if (isFirstInDay) {
            Label dateLabel = new Label(meeting.getDateTime().getDate());
            dateLabel.setStyle("-fx-text-fill: #CCB9B9");
            datePlaceholder.getChildren().add(dateLabel);
        }

        if (indexOfNextMeeting == displayedIndex) {
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
