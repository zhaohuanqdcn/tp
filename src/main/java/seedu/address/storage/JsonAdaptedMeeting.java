package seedu.address.storage;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Duration;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Title;
import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";
    public static final String PARSE_ERROR_MESSAGE_FORMAT = "Meeting's %s was incorrectly saved in the data file";
    private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("d/M/yy HHmm");

    private final String title;
    private final String duration;
    private final String dateTime;
    private final String location;
    private final List<UUID> participants = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("title") String title, @JsonProperty("duration") String duration,
            @JsonProperty("dateTime") String dateTime, @JsonProperty("company") String company,
                    @JsonProperty("location") String location,
                            @JsonProperty("participants") List<UUID> participants) {
        this.title = title;
        this.duration = duration;
        this.dateTime = dateTime;
        this.location = location;
        if (participants != null) {
            this.participants.addAll(participants);
        }
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        title = source.getTitle().value;
        duration = source.getDuration().hours + " " + source.getDuration().minutes;
        dateTime = source.getDateTime().value.format(dateTimeFormat);
        location = source.getLocation().value;
        participants.addAll(source.getParticipants().stream()
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {
//        final List<Person> meetingParticipants = new ArrayList<>();
//        for (JsonAdaptedPerson person : participants) {
//            meetingParticipants.add(person.toModelType());
//        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (duration == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Duration.class.getSimpleName()));
        }

        String[] durations = duration.split(" ");

        long hours = Long.parseLong(durations[0]);
        long minutes = Long.parseLong(durations[1]);

        if (!Duration.isValidDuration(hours, minutes)) {
            throw new IllegalValueException(Duration.MESSAGE_CONSTRAINTS);
        }
        final Duration modelDuration = new Duration(hours, minutes);

        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateTime.class.getSimpleName()));
        }

        final DateTime modelDateTime;

        try {
            modelDateTime = new DateTime(dateTime);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(String.format(PARSE_ERROR_MESSAGE_FORMAT, DateTime.class.getSimpleName()));
        }

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        final Set<UUID> modelParticipants = new HashSet<>(participants);
        return new Meeting(modelTitle, modelDuration, modelDateTime, modelLocation, modelParticipants);
    }

}
