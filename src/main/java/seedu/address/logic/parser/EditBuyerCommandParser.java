package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditBuyerCommand;
import seedu.address.logic.commands.SetBuyerPriorityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.displayable.Priority;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class EditBuyerCommandParser implements Parser<EditBuyerCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditBuyerCommand
     * and returns an EditBuyerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditBuyerCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split("\\s+");
            Index index = ParserUtil.parseIndex(splitArgs[0]);
            return new EditBuyerCommand();
        } catch (ParseException | IndexOutOfBoundsException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBuyerCommand.MESSAGE_USAGE), pe);
        }
    }
}
