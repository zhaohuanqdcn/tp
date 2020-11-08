package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_USER_PREF;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditUserPrefCommand;

public class EditUserPrefCommandParserTest {

    private static final String VALID_INPUT = " i/10";
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditUserPrefCommand.MESSAGE_USAGE);
    private static final String MESSAGE_INVALID_INTERVAL = "interval is not a valid positive integer within the range";

    private EditUserPrefCommandParser parser = new EditUserPrefCommandParser();

    @Test
    public void parse_nothingSpecify_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validInput_success() {
        EditUserPrefCommand expectedCommand = new EditUserPrefCommand(DESC_USER_PREF);
        assertParseSuccess(parser, VALID_INPUT, expectedCommand);
    }

    @Test
    public void parse_invalidValue_failure() {
        //negative value
        assertParseFailure(parser, " i/-1", MESSAGE_INVALID_INTERVAL);
        //boundary value 0
        assertParseFailure(parser, " i/0", MESSAGE_INVALID_INTERVAL);
        //double value
        assertParseFailure(parser, " i/1.0", MESSAGE_INVALID_INTERVAL);
        //Nothing specify after i/
        assertParseFailure(parser, " i/", MESSAGE_INVALID_INTERVAL);
        assertParseFailure(parser, " i/ ", MESSAGE_INVALID_INTERVAL);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        //Combining Multiple Inputs here because " i/10" has been tested in previous case
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "some random string i/10", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "n/ string i/10", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        //" i/20" should be a valid input because 20 and 10 are from the same partition
        String validInputTwo = " i/20";
        EditUserPrefCommand expectedCommand = new EditUserPrefCommand(DESC_USER_PREF);
        assertParseSuccess(parser, validInputTwo + VALID_INPUT, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        //" i/-1" has been tested to be invalid
        String invalidInput = " i/-1";
        EditUserPrefCommand expectedCommand = new EditUserPrefCommand(DESC_USER_PREF);
        assertParseSuccess(parser, invalidInput + VALID_INPUT, expectedCommand);
    }
}
