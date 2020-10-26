package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandSessionTest {

    @Test
    void scrollUp_inRange_nonEmptyString() {
        CommandSession commandSession = new CommandSession();
        commandSession.add("1");
        commandSession.add("2");

        assertEquals("2", commandSession.scrollUp());

        commandSession.add("3");

        assertEquals("3", commandSession.scrollUp());
    }

    @Test
    void scrollUp_outOfRange_firstString() {
        CommandSession commandSession = new CommandSession();
        commandSession.add("1");

        assertEquals("1", commandSession.scrollUp());
    }

    @Test
    void multipleScrollUp_inRange_nonEmptyString() {
        CommandSession commandSession = new CommandSession();
        commandSession.add("1");
        commandSession.add("2");
        commandSession.add("3");
        commandSession.add("4");

        commandSession.scrollUp();
        assertEquals("3", commandSession.scrollUp());

        commandSession.add("5");

        commandSession.scrollUp();
        assertEquals("4", commandSession.scrollUp());
    }

    @Test
    void multipleScrollUp_outOfRange_firstString() {
        CommandSession commandSession = new CommandSession();
        commandSession.add("1");
        commandSession.add("2");

        commandSession.scrollUp();
        commandSession.scrollUp();
        assertEquals("1", commandSession.scrollUp());
    }

    @Test
    void scrollDown_inRange_nonEmptyString() {
        CommandSession commandSession = new CommandSession();
        commandSession.add("1");
        commandSession.add("2");

        commandSession.scrollUp();
        commandSession.scrollUp();
        assertEquals("2", commandSession.scrollDown());

        commandSession.add("3");

        commandSession.scrollUp();
        commandSession.scrollUp();
        assertEquals("3", commandSession.scrollDown());
    }

    @Test
    void scrollDown_outOfRange_emptyString() {
        CommandSession commandSession = new CommandSession();
        commandSession.add("1");

        assertEquals("", commandSession.scrollDown());
    }

    @Test
    void multipleScrollDown_inRange_nonEmptyString() {
        CommandSession commandSession = new CommandSession();
        commandSession.add("1");
        commandSession.add("2");
        commandSession.add("3");
        commandSession.add("4");

        commandSession.scrollUp();
        commandSession.scrollUp();
        commandSession.scrollUp();
        commandSession.scrollUp();
        commandSession.scrollDown();
        assertEquals("3", commandSession.scrollDown());

        commandSession.add("5");

        commandSession.scrollUp();
        commandSession.scrollUp();
        commandSession.scrollUp();
        commandSession.scrollUp();
        commandSession.scrollDown();
        assertEquals("4", commandSession.scrollDown());
    }

    @Test
    void multipleScrollDown_outOfRange_emptyString() {
        CommandSession commandSession = new CommandSession();
        commandSession.add("1");
        commandSession.add("2");

        commandSession.scrollUp();
        commandSession.scrollUp();
        commandSession.scrollDown();
        commandSession.scrollDown();
        commandSession.scrollDown();
        assertEquals("", commandSession.scrollDown());
    }
}
