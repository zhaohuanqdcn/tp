package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.model.meeting.DataContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class FindMeetingCommandParserTest {

    private final FindMeetingCommandParser parser = new FindMeetingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindMeetingCommand expectedFindMeetingCommand =
                new FindMeetingCommand(new DataContainsKeywordsPredicate(Arrays.asList("2012", "Alice")));
        assertParseSuccess(parser, "2012 Alice", expectedFindMeetingCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 2012 \n \t Alice  \t", expectedFindMeetingCommand);
    }

}
