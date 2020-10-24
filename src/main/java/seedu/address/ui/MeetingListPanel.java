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

    /**
     * Creates a {@code MeetingListPanel} with the given {@code ObservableList} and timeline height.
     */
    public MeetingListPanel(ObservableList<Meeting> meetingList, DoubleBinding timelineHeight) {
        super(FXML);
        meetingListView.setItems(meetingList);
        meetingListView.setCellFactory(listView -> new MeetingListViewCell());

        timelineBar.heightProperty().bind(timelineHeight);
//        timelineWrapper.toBack();

        meetingList.addListener((InvalidationListener) observable -> {
            addPaddingToTimeline();
        });

        addPaddingToTimeline(true);
    }

    private void addPaddingToTimeline(boolean flip) {
        boolean isShort = meetingListView
                .lookupAll(".scroll-bar")
                .stream()
                .noneMatch(Node::isVisible);
        System.out.println(isShort);

        if (flip) {
            isShort = !isShort;
        }

        double padding = 44.5;
        if (!isShort) {
            padding -= 13.5;
        }
        HBox.clearConstraints(timelineBar);
        HBox.setMargin(timelineBar, new Insets(0, 420 + padding, 0, 0));

    }
    private void addPaddingToTimeline() {
        addPaddingToTimeline(false);
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

                addPaddingToTimeline();

                setGraphic(new MeetingCard(meeting, getIndex() + 1,
                        isFirstInDay, indexOfNextMeeting + 1).getRoot());
            }
        }
    }
}
