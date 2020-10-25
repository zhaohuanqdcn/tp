package seedu.address.model.memento;

import java.util.ArrayList;
import java.util.List;

public class History {
    private final List<RecretaryState> states = new ArrayList<>();

    public void push(RecretaryState state) {
        assert state != null;
        states.add(state);
    }

    public RecretaryState pop() {
        RecretaryState ret = states.get(states.size() - 1);
        states.remove(ret);
        return ret;
    }
}
