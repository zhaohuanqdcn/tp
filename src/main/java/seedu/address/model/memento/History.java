package seedu.address.model.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * The type History.
 */
public class History {
    private final List<RecretaryState> states = new ArrayList<>();

    /**
     * Push.
     *
     * @param state the state
     */
    public void push(RecretaryState state) {
        assert state != null;
        states.add(state);
    }

    /**
     * Pop recretary state.
     *
     * @return the recretary state
     */
    public RecretaryState pop() {
        RecretaryState ret = states.get(states.size() - 1);
        states.remove(ret);
        return ret;
    }
}
