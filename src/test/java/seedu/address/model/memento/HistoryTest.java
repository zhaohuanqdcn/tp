package seedu.address.model.memento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HistoryTest {

    @Test
    void popOnce_validCommandStates_previousCommand() {
        History history = new History();
        history.push(new RecretaryState("1", null, null));

        assertEquals("1", history.pop().getCommand());
        history.push(new RecretaryState("2", null, null));

        assertEquals("2", history.pop().getCommand());
    }

    @Test
    void popMultiple_validCommandStates_correctCommand() {
        History history = new History();
        history.push(new RecretaryState("1", null, null));
        history.push(new RecretaryState("2", null, null));
        history.push(new RecretaryState("3", null, null));
        history.push(new RecretaryState("4", null, null));

        assertEquals("4", history.pop().getCommand());
        assertEquals("3", history.pop().getCommand());
        history.push(new RecretaryState("5", null, null));

        assertEquals("5", history.pop().getCommand());
        assertEquals("2", history.pop().getCommand());
    }

}
