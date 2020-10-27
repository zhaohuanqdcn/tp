package seedu.address.model.memento;

import java.util.List;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

public class RecretaryState {

    private final List<Person> personList;
    private final List<Meeting> meetingList;
    private final String command;

    /**
     * Instantiates a new Recretary state.
     *
     * @param command     the command
     * @param personList  the person list
     * @param meetingList the meeting list
     */
    public RecretaryState(String command, List<Person> personList, List<Meeting> meetingList) {
        this.command = command;
        this.personList = personList;
        this.meetingList = meetingList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public List<Meeting> getMeetingList() {
        return meetingList;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "RecretaryState{\n"
                + "personList=" + personList
                + ",\n meetingList=" + meetingList
                + ",\n command='" + command + '\''
                + "\n}";
    }
}
