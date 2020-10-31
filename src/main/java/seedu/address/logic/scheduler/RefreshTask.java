package seedu.address.logic.scheduler;

import java.time.LocalDateTime;

import javafx.application.Platform;
import seedu.address.logic.Logic;
import seedu.address.model.meeting.Meeting;

public class RefreshTask extends ScheduledTask {

    private final Logic logic;

    /**
     * Instantiates a new Refresh task.
     *
     * @param scheduler the scheduler to which this task in bound
     * @param logic     the logic from which the order of meetings are generated
     * @param name      the name of the task
     */
    public RefreshTask(Scheduler scheduler, Logic logic, String name) {
        super(scheduler, logic.getNextMeeting(0), name);
        this.logic = logic;
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
