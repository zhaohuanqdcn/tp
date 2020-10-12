package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteParticipantCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteParticipantCommandParser implements Parser<DeleteParticipantCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteParticipantCommand
     * and returns a DeleteParticipantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteParticipantCommand parse(String args) throws ParseException {

        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_CONTACT_INDEX, PREFIX_MEETING_INDEX);

            if (!arePrefixesPresent(argMultimap, PREFIX_CONTACT_INDEX)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteParticipantCommand.MESSAGE_USAGE));
            }

            Index participantIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CONTACT_INDEX).get());
            Index meetingIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MEETING_INDEX).get());

            return new DeleteParticipantCommand(participantIndex, meetingIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteParticipantCommand.MESSAGE_USAGE), pe);
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
