package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.meeting.Meeting;

/**
 * Panel containing the list of meetings.
 */
public class MeetingListPanel extends UiPart<Region> {
    private static final String FXML = "MeetingListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MeetingListPanel.class);

    @FXML
    private ListView<Meeting> meetingListView;

    /**
     * Creates a {@code MeetingListPanel} with the given {@code ObservableList}.
     */
    public MeetingListPanel(Logic logic) {
        super(FXML);
        meetingListView.setItems(logic.getFilteredMeetingList());
        meetingListView.setCellFactory(listView -> new MeetingListViewCell(logic));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Meeting} using a {@code MeetingCard}.
     */
    class MeetingListViewCell extends ListCell<Meeting> {

        private final Logic logic;

        public MeetingListViewCell(Logic logic) {
            this.logic = logic;
        }
        @Override
        protected void updateItem(Meeting meeting, boolean empty) {
            super.updateItem(meeting, empty);

            if (empty || meeting == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MeetingCard(logic, meeting, getIndex() + 1).getRoot());
            }
        }
    }

}
