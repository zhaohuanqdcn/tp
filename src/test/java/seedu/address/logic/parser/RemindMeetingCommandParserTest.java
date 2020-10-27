package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemindMeetingCommand;
import seedu.address.model.meeting.MeetingWithinDaysPredicate;

public class RemindMeetingCommandParserTest {

    private RemindMeetingCommandParser parser = new RemindMeetingCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        MeetingWithinDaysPredicate predicate = new MeetingWithinDaysPredicate(10);
        assertParseSuccess(parser, "10", new RemindMeetingCommand(predicate));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemindMeetingCommand.MESSAGE_USAGE));
    }
}
