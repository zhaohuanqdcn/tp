package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.meeting.Meeting;

/**
 * Panel containing the list of meetings.
 */
public class MeetingListPanel extends UiPart<Region> {
    private static final String FXML = "MeetingListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MeetingListPanel.class);

    @FXML
    private Rectangle timelineBar;
    @FXML
    private HBox timelineWrapper;

    @FXML
    private ListView<Meeting> meetingListView;

    private DoubleBinding timelineHeight;
    /**
     * Creates a {@code MeetingListPanel} with the given {@code ObservableList} and timeline height.
     */
    public MeetingListPanel(ObservableList<Meeting> meetingList, DoubleBinding timelineHeight) {
        super(FXML);
        meetingListView.setItems(meetingList);
        meetingListView.setCellFactory(listView -> new MeetingListViewCell());

        timelineBar.heightProperty().bind(timelineHeight);

        meetingList.addListener((InvalidationListener) observable -> {
            addPaddingToTimeline();
        });

        addPaddingToTimeline();
    }

    public void addPaddingToTimeline() {
        // inverted as it runs before the update flip
        boolean isShort = meetingListView
                .lookupAll(".scroll-bar")
                .stream()
                .limit(1)
                .noneMatch(Node::isVisible);

        System.out.println(isShort);

        if (!isShort) {
            HBox.setMargin(timelineBar, new Insets(0, 451, 0, 0));
        } else {
            HBox.setMargin(timelineBar, new Insets(0, 464.5, 0, 0));
        }

    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Meeting} using a {@code MeetingCard}.
     */
    class MeetingListViewCell extends ListCell<Meeting> {
        @Override
        protected void updateItem(Meeting meeting, boolean empty) {
            super.updateItem(meeting, empty);

            if (empty || meeting == null) {
                setGraphic(null);
                setText(null);

                this.setStyle("-fx-background-color: black");
                timelineWrapper.toFront();
                this.setStyle("-fx-background-color: transparent");
                meetingListView.toFront();
            } else {
                int indexOfNextMeeting = -1;

                for (Meeting m : meetingListView.getItems()) {
                    if (m.getDateTime().value.isAfter(LocalDateTime.now())) {
                        indexOfNextMeeting = meetingListView.getItems().indexOf(m);
                        break;
                    }
                }

                boolean isFirstInDay = true;
                if (getIndex() > 0) {
                    isFirstInDay = !meetingListView
                            .getItems()
                            .get(getIndex() - 1)
                            .getDateTime()
                            .getDate()
                            .equals(meeting.getDateTime().getDate());
                }

                setGraphic(new MeetingCard(meeting, getIndex() + 1,
                        isFirstInDay, indexOfNextMeeting + 1).getRoot());
            }
        }
    }
}
