package seedu.address.model.memento;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

import java.util.List;

/**
 * History class that stores all the <code>RecretaryStates</code>.
 * This can be viewed as the Originator in the Memento design pattern.
 */
public class StateManager {

    private String commandState;
    private List<Person> personList;

    private List<Meeting> meetingList;

    public RecretaryState createState() {
        return new RecretaryState(commandState, personList, meetingList);
    }

    public void restoreState(RecretaryState recretaryState) {
        commandState = recretaryState.getCommand();
        personList = recretaryState.getPersonList();
        meetingList = recretaryState.getMeetingList();
    }

    public String getCommand() {
        return commandState;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public List<Meeting> getMeetingList() {
        return meetingList;
    }

    public void setCommandState(String state) {
        this.commandState = state;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public void setMeetingList(List<Meeting> meetingList) {
        this.meetingList = meetingList;
    }
}
