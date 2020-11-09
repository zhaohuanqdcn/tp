package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemindMeetingCommand;
import seedu.address.model.meeting.MeetingWithinHoursPredicate;

public class RemindMeetingCommandParserTest {

    private RemindMeetingCommandParser parser = new RemindMeetingCommandParser();

    // The following test cases are designed by using equivalence partitioning and boundary value analysis

    @Test
    public void parse_validInputNormal_returnsRemindCommand() {
        MeetingWithinHoursPredicate predicate = new MeetingWithinHoursPredicate(10);
        assertParseSuccess(parser, "10", new RemindMeetingCommand(predicate));
    }

    @Test
    public void parse_validInputBoundary_returnsRemindCommand() {
        MeetingWithinHoursPredicate predicate = new MeetingWithinHoursPredicate(1);
        assertParseSuccess(parser, "1", new RemindMeetingCommand(predicate));
    }

    @Test
    public void parse_invalidInputZero_throwsParseException() {
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemindMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidInputNegative_throwsParseExceptioon() {
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemindMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidInputString_throwsParseException() {
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemindMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidInputDouble_throwsParseException() {
        assertParseFailure(parser, "10.0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemindMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemindMeetingCommand.MESSAGE_USAGE));
    }


}
