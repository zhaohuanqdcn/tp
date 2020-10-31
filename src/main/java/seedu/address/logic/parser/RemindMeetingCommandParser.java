package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.RemindMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.MeetingWithinHoursPredicate;

/**
 * Parses input arguments and creates a new RemindMeetingCommand object
 */
public class RemindMeetingCommandParser implements Parser<RemindMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemindMeetingCommand
     * and returns a RemindMeetingCommand object for execution.
     * @throws  if the user input does not conform the expected format
     */
    public RemindMeetingCommand parse(String args) throws ParseException {
        try {
            int hour = ParserUtil.parseHour(args);
            return new RemindMeetingCommand(new MeetingWithinHoursPredicate(hour));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindMeetingCommand.MESSAGE_USAGE), pe);
        }
    }
}
