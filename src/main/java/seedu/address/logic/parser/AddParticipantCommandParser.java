package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddParticipantCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddParticipantCommandParser implements Parser<AddParticipantCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddParticipantCommand
     * and returns a AddParticipantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddParticipantCommand parse(String args) throws ParseException {

        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_CONTACT_INDEX, PREFIX_MEETING_INDEX);

            if (!arePrefixesPresent(argMultimap, PREFIX_CONTACT_INDEX)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddParticipantCommand.MESSAGE_USAGE));
            }

            Index participantIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CONTACT_INDEX).get());
            Index meetingIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MEETING_INDEX).get());

            return new AddParticipantCommand(participantIndex, meetingIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddParticipantCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
