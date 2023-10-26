package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SetSellerPriorityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.displayable.Priority;

/**
 * Parses input arguments and creates a new SetBuyerPriorityCommand object
 */
public class SetSellerPriorityCommandParser implements Parser<SetSellerPriorityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetSellerPriorityCommand
     * and returns a SetSellerPriorityCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetSellerPriorityCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split("\\s+");
            Index index = ParserUtil.parseIndex(splitArgs[0]);
            Priority priority = ParserUtil.parsePriority(splitArgs[1]);
            return new SetSellerPriorityCommand(index, priority);
        } catch (ParseException | IndexOutOfBoundsException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSellerPriorityCommand.MESSAGE_USAGE), pe);
        }
    }
}
