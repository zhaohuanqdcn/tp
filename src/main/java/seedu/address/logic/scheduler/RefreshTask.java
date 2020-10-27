package seedu.address.logic.scheduler;

import java.time.LocalDateTime;

import javafx.application.Platform;
import seedu.address.logic.Logic;
import seedu.address.model.meeting.Meeting;

public class RefreshTask extends ScheduledTask {

    public RefreshTask(Scheduler scheduler, Logic logic, String name) {
        super(scheduler, logic, name);
    }

    @Override
    public Meeting getMeeting() {
        return super.meeting;
    }

    @Override
    public LocalDateTime getTaskTime() {
        return super.meeting.getDateTime().value;
    }

    @Override
    public void run() {
        // update scheduler
        scheduler.resetNextTaskTime();
        RefreshTask nextRefresh = new RefreshTask(scheduler, logic, name);
        scheduler.update(nextRefresh);

        //update Ui
        Platform.runLater(logic::refreshApplication);
    }

    @Override
    public String toString() {
        return this.name + " " + this.getTaskTime();
    }
}
