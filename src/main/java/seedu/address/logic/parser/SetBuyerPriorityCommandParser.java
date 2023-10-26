package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SetBuyerPriorityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.displayable.Priority;

import java.util.Arrays;

/**
 * Parses input arguments and creates a new SetBuyerPriorityCommand object
 */
public class SetBuyerPriorityCommandParser implements Parser<SetBuyerPriorityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetBuyerPriorityCommand
     * and returns a SetBuyerPriorityCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetBuyerPriorityCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split("\\s+");
            System.out.println(Arrays.toString(splitArgs));
            Index index = ParserUtil.parseIndex(splitArgs[0]);
            Priority priority = ParserUtil.parsePriority(splitArgs[1]);
            return new SetBuyerPriorityCommand(index, priority);
        } catch (ParseException | IndexOutOfBoundsException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBuyerPriorityCommand.MESSAGE_USAGE), pe);
        }
    }
}
