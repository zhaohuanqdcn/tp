package seedu.address.ui;

import java.util.UUID;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import java.time.LocalDateTime;

import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

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
    public MeetingListPanel(ObservableList<Meeting> meetingList, ObservableMap<UUID, Person> personMap,
                            DoubleBinding timelineHeight) {
        super(FXML);
        meetingListView.setItems(meetingList);
        meetingListView.setCellFactory(listView -> new MeetingListViewCell(personMap));

        timelineBar.heightProperty().bind(timelineHeight);

        HBox.setMargin(timelineBar, new Insets(0, 0, 0, 114));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Meeting} using a {@code MeetingCard}.
     */
    class MeetingListViewCell extends ListCell<Meeting> {

        private final ObservableMap<UUID, Person> personMap;

        public MeetingListViewCell(ObservableMap<UUID, Person> personMap) {
            this.personMap = personMap;
        }
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
              
                int indexOfNextEarliestMeeting = -1;

                for (Meeting m : meetingListView.getItems()) {
                    if (m.getDateTime().value.isAfter(LocalDateTime.now())) {
                        indexOfNextEarliestMeeting = meetingListView.getItems().indexOf(m);
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

                if (getIndex() == meetingListView.getItems().size() - 1) {
                    boolean isScrollBarVisible = meetingListView
                            .lookupAll(".scroll-bar")
                            .stream()
                            .map(node -> (ScrollBar) node)
                            .filter(scrollBar -> scrollBar.getOrientation().equals(Orientation.VERTICAL))
                            .anyMatch(Node::isVisible);

                    logger.info("isScrollBarVisible? " + isScrollBarVisible);
                }

                setGraphic(new MeetingCard(personMap, meeting, getIndex() + 1,
                        isFirstInDay, indexOfNextEarliestMeeting + 1).getRoot());
            }
        }
    }
}
