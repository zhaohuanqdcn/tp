package seedu.address.logic.scheduler;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;
import seedu.address.model.meeting.Meeting;

public class Scheduler {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);
    private Timer timer;
    private LocalDateTime nextTask;

    /**
     * Constructor for Scheduler
     */
    public Scheduler() {
        this.timer = new Timer();
        this.nextTask = null;
    }

    /**
     * Asks {@code Scheduler} to schedule a {@code task}. If the task has no scheduled meeting or the time is later
     * the already scheduled task, then the {@code task} will not be scheduled.
     */
    public void update(ScheduledTask task) {
        Meeting meeting = task.getMeeting();
        if (meeting != null) {
            LocalDateTime time = task.getTaskTime();
            assert time != null;
            if (nextTask == null || time.isBefore(nextTask)) {
                scheduleNewTask(task, time);
            }
        }
    }

    private void scheduleNewTask(ScheduledTask task, LocalDateTime time) {
        Date date = convertToDate(time);
        this.cancel();
        timer = new Timer();
        timer.schedule(task, date);
        nextTask = time;
        logger.info("----------------[SCHEDULE][" + task.toString() + "]");
    }

    /**
     * Cancels the scheduled task
     */
    public void cancel() {
        this.timer.cancel();
    }

    protected void resetNextTaskTime() {
        this.nextTask = null;
    }

    public static Date convertToDate(LocalDateTime dateToConvert) {
        return Timestamp.valueOf(dateToConvert);
    }

}
