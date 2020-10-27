package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_PARTICIPANTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_PARTICIPANTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditMeetingCommandParser implements Parser<EditMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditContactCommand
     * and returns an EditContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DURATION, PREFIX_DATETIME,
                        PREFIX_LOCATION, PREFIX_ADD_PARTICIPANTS, PREFIX_DELETE_PARTICIPANTS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditMeetingCommand.MESSAGE_USAGE), pe);
        }

        EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor = new EditMeetingCommand.EditMeetingDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editMeetingDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            editMeetingDescriptor.setDateTime(ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get()));
        }
        if (argMultimap.getValue(PREFIX_DURATION).isPresent()) {
            editMeetingDescriptor.setDuration(ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editMeetingDescriptor.setLocation(ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }
        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMeetingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMeetingCommand(index, editMeetingDescriptor);
    }

}
