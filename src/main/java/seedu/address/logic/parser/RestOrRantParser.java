package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODE;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.Mode;
import seedu.address.logic.commands.*;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class RestOrRantParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(Mode mode, String userInput) throws ParseException, CommandException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // General commands that work in all modes
        switch (commandWord) {
            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();
                
            case HelpCommand.COMMAND_ALIAS:
                return new HelpCommand();

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
                
            case ExitCommand.COMMAND_ALIAS:
                return new ExitCommand();

            case RestaurantModeCommand.COMMAND_WORD:
                return new RestaurantModeCommand();

            case MenuModeCommand.COMMAND_WORD:
                return new MenuModeCommand();

            case TableModeCommand.COMMAND_WORD:
                return new TableModeCommandParser().parse(arguments);
                
            case AddTableCommand.COMMAND_WORD:
                if (mode != Mode.RESTAURANT_MODE) {
                    throw new ParseException(MESSAGE_INVALID_MODE);
                }
                return new AddTableCommandParser().parse(arguments);

            case AddItemToMenuCommand.COMMAND_WORD:
                if (mode != Mode.MENU_MODE) {
                    throw new ParseException(MESSAGE_INVALID_MODE);
                }
                return new AddItemToMenuCommandParser().parse(arguments);

            case AddOrderCommand.COMMAND_WORD:
                if (mode != Mode.TABLE_MODE) {
                    throw new ParseException(MESSAGE_INVALID_MODE);
                }
                return new AddOrderCommandParser().parse(arguments);

            case BillCommand.COMMAND_WORD:
                if (mode != Mode.TABLE_MODE) {
                    throw new ParseException(MESSAGE_INVALID_MODE);
                }
                return new BillCommandParser().parse(arguments);     
            default:
                break;
        }

        throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

    }
    
}
