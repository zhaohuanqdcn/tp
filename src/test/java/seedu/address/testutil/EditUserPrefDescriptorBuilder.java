package seedu.address.testutil;

import seedu.address.logic.commands.EditUserPrefCommand.EditUserPrefDescriptor;
import seedu.address.model.UserPrefs;

public class EditUserPrefDescriptorBuilder {
    private EditUserPrefDescriptor descriptor;

    public EditUserPrefDescriptorBuilder() {
        descriptor = new EditUserPrefDescriptor();
    }

    public EditUserPrefDescriptorBuilder(EditUserPrefDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    public EditUserPrefDescriptorBuilder(UserPrefs userPrefs) {
        descriptor.setInterval(userPrefs.getIntervalBetweenMeetings());
    }

    /**
     * Sets the {@code interval} of the {@code EditUserPrefDescriptor} that we are building.
     */
    public EditUserPrefDescriptorBuilder withInterval(int interval) {
        descriptor.setInterval(interval);
        return this;
    }

    public EditUserPrefDescriptor build() {
        return descriptor;
    }
}
