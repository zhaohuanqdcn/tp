package seedu.address.model.memento;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

import java.util.List;

public class RecretaryState {

    private final List<Person> personList;
    private final List<Meeting> meetingList;
    private final String command;

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
        return "RecretaryState{\n" +
                "personList=" + personList +
                ",\n meetingList=" + meetingList +
                ",\n command='" + command + '\'' +
                "\n}";
    }
}
