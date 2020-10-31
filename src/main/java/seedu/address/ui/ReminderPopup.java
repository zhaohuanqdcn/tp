package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.meeting.Meeting;

/**
 * Controller for a help page
 */
public class ReminderPopup extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(ReminderPopup.class);
    private static final String FXML = "ReminderPopup.fxml";

    private Meeting meeting;

    @FXML
    private Button dismissButton;

    @FXML
    private Label title;

    @FXML
    private Label time;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public ReminderPopup(Stage root, Stage appStage) {
        super(FXML, root);
        root.initStyle(StageStyle.TRANSPARENT);
        root.initOwner(appStage);
        root.initModality(Modality.APPLICATION_MODAL);
        root.getScene().setFill(Color.TRANSPARENT);
    }

    /**
     * Creates a new HelpWindow.
     */
    public ReminderPopup(Stage appStage) {
        this(new Stage(), appStage);
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException <ul>
     *  <li>
     *      if this method is called on a thread other than the JavaFX Application Thread.
     *  </li>
     *  <li>
     *      if this method is called during animation or layout processing.
     *  </li>
     *  <li>
     *      if this method is called on the primary stage.
     *  </li>
     *  <li>
     *      if {@code dialogStage} is already showing.
     *  </li>
     * </ul>
     */
    public void show() {
        assert meeting != null;
        logger.fine("Showing popup notification about the application.");
        updateUI();
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        updateUI();
        getRoot().requestFocus();
    }

    @FXML
    public void dismiss() {
        getRoot().hide();
    }

    /**
     * Update ui of the popup with the meeting and play sound.
     */
    public void updateUI() {
        title.setText(meeting.getTitle().toString());
        time.setText(meeting.getDateTime().getStartTime()
                + " to " + meeting.getDateTime().getEndTime(meeting.getDuration()));
        String path = getClass().getResource("/sounds/notification.mp3").toString();
        MediaPlayer mp = new MediaPlayer(new Media(path));
        mp.play();
    }
}
