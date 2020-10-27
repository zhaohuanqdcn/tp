package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USER_PREFERENCE_INTERVAL;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.UserPrefs;

public class EditUserPrefCommand extends Command {

    public static final String COMMAND_WORD = "edit_userPref";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits user preference. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INTERVAL (must be a none negative integer) "
            + "[" + PREFIX_USER_PREFERENCE_INTERVAL + "INTERVAL] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_USER_PREFERENCE_INTERVAL + "10";


    public static final String MESSAGE_EDIT_USER_PREFERENCE_SUCCESS = "Edited user preference:\n";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String INVALID_NUMERIC_INPUT = "The interval has to be an none negative integer.";

    private final EditUserPrefDescriptor userPrefsDescriptor;

    /**
     * @param userPrefsDescriptor details to edit the user preference with
     */
    public EditUserPrefCommand(EditUserPrefDescriptor userPrefsDescriptor) {
        requireNonNull(userPrefsDescriptor);
        this.userPrefsDescriptor = userPrefsDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        UserPrefs userPrefCopy = new UserPrefs(model.getUserPrefs());

        if (userPrefsDescriptor.getInterval().isPresent()) {
            int interval = userPrefsDescriptor.getInterval().get();
            if (interval < 0) {
                throw new CommandException(INVALID_NUMERIC_INPUT);
            }
            userPrefCopy.setIntervalBetweenMeetings(interval);
        }

        model.setUserPrefs(userPrefCopy);
        return new CommandResult(MESSAGE_EDIT_USER_PREFERENCE_SUCCESS + userPrefCopy.toString());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static class EditUserPrefDescriptor {
        private int interval;

        public EditUserPrefDescriptor() {}

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(interval);

        }

        public void setInterval(int interval) {
            this.interval = interval;
        }

        public Optional<Integer> getInterval() {
            return Optional.ofNullable(interval);
        }
    }

}
