package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteBuyerCommand;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class DeleteBuyerCommandParserTest {

    DeleteBuyerCommandParser parser = new DeleteBuyerCommandParser();

    @Test
    public void parse_zeroInput_exceptionThrown() {
        assertParseFailure(parser, "0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBuyerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeInput_exceptionThrown() {
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBuyerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonZeroUnsignedInteger_success() {
        assertParseSuccess(parser, "1", new DeleteBuyerCommand(Index.fromZeroBased(1)));

        assertParseSuccess(parser, "10", new DeleteBuyerCommand(Index.fromZeroBased(10)));

        assertParseSuccess(parser, "100", new DeleteBuyerCommand(Index.fromZeroBased(100)));
    }
}
