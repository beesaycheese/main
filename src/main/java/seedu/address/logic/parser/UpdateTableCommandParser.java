package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.UpdateTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UpdateTableCommand object
 */
public class UpdateTableCommandParser implements Parser<UpdateTableCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateTableCommand
     * and returns an UpdateTableCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateTableCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] splitArgs = trimmedArgs.split("\\s+");
        if (trimmedArgs.isEmpty() || splitArgs.length != 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateTableCommand.MESSAGE_USAGE));
        }

        return new UpdateTableCommand(splitArgs);
    }
}