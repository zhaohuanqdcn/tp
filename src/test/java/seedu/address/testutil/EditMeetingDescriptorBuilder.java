package seedu.address.testutil;

import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Duration;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Title;

public class EditMeetingDescriptorBuilder {

    private EditMeetingCommand.EditMeetingDescriptor descriptor;

    public EditMeetingDescriptorBuilder() {
        descriptor = new EditMeetingCommand.EditMeetingDescriptor();
    }

    public EditMeetingDescriptorBuilder(EditMeetingCommand.EditMeetingDescriptor descriptor) {
        this.descriptor = new EditMeetingCommand.EditMeetingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMeetingDescriptor} with fields containing {@code meeting}'s details
     */
    public EditMeetingDescriptorBuilder(Meeting meeting) {
        descriptor = new EditMeetingCommand.EditMeetingDescriptor();
        descriptor.setTitle(meeting.getTitle());
        descriptor.setDuration(meeting.getDuration());
        descriptor.setDateTime(meeting.getDateTime());
        descriptor.setLocation(meeting.getLocation());
        descriptor.setPersons(meeting.getParticipants());
    }

    /**
     * Sets the {@code Title} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDuration(Duration duration) {
        descriptor.setDuration(duration);
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDateTime(String dateTime) {
        descriptor.setDateTime(new DateTime(dateTime));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    public EditMeetingCommand.EditMeetingDescriptor build() {
        return descriptor;
    }
}
