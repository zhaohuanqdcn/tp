package seedu.address.logic.scheduler;

import javafx.application.Platform;
import seedu.address.logic.Logic;
import seedu.address.model.meeting.Meeting;

import java.time.LocalDateTime;
import java.util.function.Consumer;

public class ReminderPopupTask extends ScheduledTask {

    private final Logic logic;
    Consumer<Meeting> consumer;

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
