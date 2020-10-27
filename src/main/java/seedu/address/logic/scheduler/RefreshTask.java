package seedu.address.logic.scheduler;

import java.time.LocalDateTime;

import javafx.application.Platform;
import seedu.address.logic.Logic;
import seedu.address.model.meeting.Meeting;

public class RefreshTask extends ScheduledTask {
    private Meeting meeting;
    private final Scheduler scheduler;
    private final Logic logic;
    private final String name;

    /**
     * constructor for Refresh Task
     */
    public RefreshTask(Scheduler scheduler, Logic logic, String name) {
        super();
        this.scheduler = scheduler;
        this.logic = logic;
        this.meeting = this.logic.getFirstFutureMeeting();
        this.name = name;
    }

    @Override
    public Meeting getMeeting() {
        return this.meeting;
    }

    @Override
    public LocalDateTime getTaskTime() {
        return this.meeting.getDateTime().value;
    }

    @Override
    public void run() {
        meeting = logic.getFirstFutureMeeting();
        scheduler.resetNextTaskTime();
        RefreshTask nextRefresh = new RefreshTask(scheduler, logic, name);
        scheduler.update(nextRefresh);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                logic.refreshApplication();
            }
        });
    }

    @Override
    public String toString() {
        return this.name + " " + this.getTaskTime();
    }
}
