package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.CommandWarnings;
import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.logic.commands.AddSellerCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteBuyerCommand;
import seedu.address.logic.commands.DeleteSellerCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListBuyersCommand;
import seedu.address.logic.commands.ListSellersCommand;
import seedu.address.logic.commands.SetBuyerPriorityCommand;
import seedu.address.logic.commands.SetSellerPriorityCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @param commandWarnings  A warning container.
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, CommandWarnings commandWarnings) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddBuyerCommand.COMMAND_WORD:
            return new AddBuyerCommandParser().parse(arguments, commandWarnings);

        case AddSellerCommand.COMMAND_WORD:
            return new AddSellerCommandParser().parse(arguments, commandWarnings);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments, commandWarnings);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ListSellersCommand.COMMAND_WORD:
            return new ListSellersCommand();

        case ListBuyersCommand.COMMAND_WORD:
            return new ListBuyersCommand();

        case DeleteBuyerCommand.COMMAND_WORD:
            return new DeleteBuyerCommandParser().parse(arguments, commandWarnings);

        case DeleteSellerCommand.COMMAND_WORD:
            return new DeleteSellerCommandParser().parse(arguments, commandWarnings);

        case SetBuyerPriorityCommand.COMMAND_WORD:
            return new SetBuyerPriorityCommandParser().parse(arguments, commandWarnings);

        case SetSellerPriorityCommand.COMMAND_WORD:
            return new SetSellerPriorityCommandParser().parse(arguments, commandWarnings);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    public Command parseCommand(String userInput) throws ParseException {
        return parseCommand(userInput, new CommandWarnings());
    }

}
