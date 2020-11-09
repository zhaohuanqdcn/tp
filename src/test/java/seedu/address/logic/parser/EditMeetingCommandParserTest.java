package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_DISCUSSION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECURRING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_DISCUSSION;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_DISCUSSION;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_ROUNDTABLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DISCUSSION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Duration;

public class EditMeetingCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE);

    private final EditMeetingCommandParser parser = new EditMeetingCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TITLE_DISCUSSION, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditMeetingCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TITLE_DESC_DISCUSSION, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TITLE_DESC_ROUNDTABLE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_DATETIME_DESC, DateTime.MESSAGE_CONSTRAINTS); // invalid datetime

        assertParseFailure(parser, "1" + INVALID_DURATION_DESC + DATETIME_DESC_DISCUSSION,
                Duration.MESSAGE_CONSTRAINTS); // invalid duration

        assertParseFailure(parser, "1" + INVALID_RECURRING_DESC,
                "Invalid command format! \n" + EditMeetingCommand.MESSAGE_USAGE); // no recurrence allowed

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_DATETIME_DESC + INVALID_DURATION_DESC + LOCATION_DESC_DISCUSSION,
                DateTime.MESSAGE_CONSTRAINTS);
    }

    /*
    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_MEETING;
        String userInput = targetIndex.getOneBased() + DATETIME_DESC_DISCUSSION + DURATION_DESC_ROUNDTABLE
                + LOCATION_DESC_DISCUSSION + TITLE_DESC_ROUNDTABLE;
        System.out.println(userInput);

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withDateTime(VALID_DATETIME_DISCUSSION).withDuration(VALID_DURATION_ROUNDTABLE)
                .withLocation(VALID_LOCATION_DISCUSSION).withTitle(VALID_TITLE_ROUNDTABLE).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = INDEX_FIRST_MEETING;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_ROUNDTABLE ;
        System.out.println(userInput);

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withTitle(VALID_TITLE_ROUNDTABLE).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
    */
}
