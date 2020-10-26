package seedu.address.model.memento;

import java.util.Stack;

/**
 * History class that stores all the <code>RecretaryStates</code>.
 * This can be viewed as the Caretaker in the Memento design pattern.
 */
public class History {
    private final Stack<RecretaryState> states = new Stack<>();

    /**
     * Push a state into the history
     *
     * @param state the state
     */
    public void push(RecretaryState state) {
        assert state != null;
        if (!state.getCommand().stripLeading().startsWith("undo")) {
            states.push(state);
        }
    }

    /**
     * Pop recretary state from history.
     *
     * @return the recretary state that is popped
     */
    public RecretaryState pop() {
        assert !states.empty();
        return states.pop();
    }

    /**
     * Returns size of history stack
     */
    public int getSize() {
        return states.size();
    }

    @Override
    public String toString() {
        return "HistoryStack{\n" + "state: " + states + "\n}";
    }
}
