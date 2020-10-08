package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.commands.AddParticipantCommand;
import seedu.address.logic.commands.DeleteContactCommand;
import seedu.address.logic.commands.FindContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.Arrays;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

public class AddParticipantCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddParticipantCommand
     * and returns a AddParticipantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddParticipantCommand parse(String args) throws ParseException {

        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_INDEX);

            if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_INDEX)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
            }

            String name = argMultimap.getValue(PREFIX_NAME).get();
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());

            String[] nameKeywords = name.split("\\s+");

            return new AddParticipantCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)), index);
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
