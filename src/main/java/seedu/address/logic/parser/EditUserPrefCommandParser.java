package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USER_PREFERENCE_INTERVAL;

import seedu.address.logic.commands.EditUserPrefCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditUserPrefCommandParser implements Parser<EditUserPrefCommand> {

    protected static final String INTERVAL_KEYWORD = "interval";

    /**
     * Parses the given {@code String} of arguments in the context of the EditUserPrefCommand
     * and returns an EditUserPrefCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditUserPrefCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_USER_PREFERENCE_INTERVAL);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditUserPrefCommand.MESSAGE_USAGE));
        }

        EditUserPrefCommand.EditUserPrefDescriptor editUserPrefDescriptor =
                new EditUserPrefCommand.EditUserPrefDescriptor();

        if (argMultimap.getValue(PREFIX_USER_PREFERENCE_INTERVAL).isPresent()) {
            editUserPrefDescriptor.setInterval(ParserUtil.parsePositiveInteger(argMultimap
                    .getValue(PREFIX_USER_PREFERENCE_INTERVAL).get(), INTERVAL_KEYWORD));
        }

        if (!editUserPrefDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditUserPrefCommand.MESSAGE_USAGE));
        }

        return new EditUserPrefCommand(editUserPrefDescriptor);



    }



}
