package seedu.address.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * The Command Session which stores command history
 */
public class CommandSession {
    private final List<String> commands = new ArrayList<>();
    private int scrollIndex;

    /**
     * Instantiates a new Command Session with a history of .
     */
    public CommandSession() {
        scrollIndex = 0;
        commands.add("");
    }

    /**
     * Add a successful command string to history
     *
     * @param command the successful command
     */
    public void add(String command) {
        assert !command.equals("");
        commands.add(commands.size() - 1, command);
        scrollIndex = commands.size() - 1;
    }

    /**
     * Scrolls up the history and returns the correct string.
     * Called on UP click event.
     *
     * @return the command text
     */
    public String scrollUp() {
        scrollIndex--;
        if (scrollIndex < 0) {
            scrollIndex = 0;
        }
        return commands.get(scrollIndex);
    }

    /**
     * Scrolls up the history and returns the correct string.
     * Called on DOWN click event.
     *
     * @return the command text
     */
    public String scrollDown() {
        scrollIndex++;
        if (scrollIndex > commands.size() - 1) {
            scrollIndex = commands.size() - 1;
        }
        return commands.get(scrollIndex);
    }
}