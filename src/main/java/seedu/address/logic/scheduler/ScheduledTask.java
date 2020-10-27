package seedu.address.logic.scheduler;

import java.time.LocalDateTime;
import java.util.TimerTask;

import seedu.address.model.meeting.Meeting;

public abstract class ScheduledTask extends TimerTask {

    abstract Meeting getMeeting();

    abstract LocalDateTime getTaskTime();
}
