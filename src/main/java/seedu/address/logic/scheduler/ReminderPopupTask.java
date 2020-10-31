package seedu.address.logic.scheduler;

import java.time.LocalDateTime;
import java.util.function.Consumer;

import javafx.application.Platform;
import seedu.address.logic.Logic;
import seedu.address.model.meeting.Meeting;

public class ReminderPopupTask extends ScheduledTask {

    private final Logic logic;
    private final Consumer<Meeting> consumer;

    /**
     * Instantiates a new Reminder popup task.
     *
     * @param scheduler the scheduler to which this task is bound
     * @param logic     the logic used to get the order of meetings to set the task
     * @param name      the name of the task
     * @param consumer  the consumer which is called when the task is run
     */
    public ReminderPopupTask(Scheduler scheduler, Logic logic, String name, Consumer<Meeting> consumer) {
        super(scheduler, logic.getNextMeeting(30), name);
        this.consumer = consumer;
        this.logic = logic;
    }

    @Override
    public Meeting getMeeting() {
        return super.meeting;
    }

    @Override
    public LocalDateTime getTaskTime() {
        return super.meeting.getDateTime().value.minusMinutes(30);
    }

    @Override
    public void run() {
        // update scheduler
        scheduler.resetNextTaskTime();
        ReminderPopupTask nextRefresh = new ReminderPopupTask(scheduler, logic, name, consumer);
        scheduler.update(nextRefresh);

        //update Ui
        Platform.runLater(() -> consumer.accept(meeting));
    }

    @Override
    public String toString() {
        return this.name + " " + this.getTaskTime();
    }
}
