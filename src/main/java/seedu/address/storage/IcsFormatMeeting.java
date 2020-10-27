package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.meeting.Duration;
import seedu.address.model.meeting.Meeting;

/**
 * iCal version of {@link Meeting}.
 */
class IcsFormatMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";
    public static final String PARSE_ERROR_MESSAGE_FORMAT = "Meeting's %s was incorrectly saved in the data file";
    private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("d/M/yy HHmm");


    private String eventBegin = "BEGIN:VEVENT";
    private String eventEnd = "END:VEVENT";
    private String uid = "UID:";
    private String dateStamp = "DTSTAMP:";
    private String dateStart = "DTSTART;VALUE=DATE:";
    private String duration = "DURATION:PT";
    private String title = "SUMMARY:";
    private String location = "LOCATION:";
    private String recurrence = "RRULE:FREQ=";

    private DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("YYYYMMdd");
    private DateTimeFormatter timePattern = DateTimeFormatter.ofPattern("HHmmss");

    private final List<JsonAdaptedPerson> participants = new ArrayList<>();

    public String formatDate(LocalDateTime in) {
        return in.format(datePattern) + "T" + in.format(timePattern) + "Z";
    }

    public String formatDuration(Duration in) {
        return in.getHours() + "H" + in.getMinutes() + "M";
    }

    public String toString(Meeting meeting) {
        StringBuilder builder = new StringBuilder();
        builder.append(eventBegin).append("\r\n");
        builder.append(uid).append("recretary").append(formatDate(LocalDateTime.now()))
                .append(meeting.hashCode()).append("\r\n");
        builder.append(dateStamp).append(formatDate(LocalDateTime.now())).append("\r\n");
        builder.append(dateStart).append(formatDate(meeting.getDateTime().getValue())).append("\r\n");
        builder.append(duration).append(formatDuration(meeting.getDuration())).append("\r\n");
        builder.append(title).append(meeting.getTitle()).append("\r\n");
        builder.append(location).append(meeting.getLocation()).append("\r\n");
        builder.append(eventEnd).append("\r\n");
        return builder.toString();
    }

}
