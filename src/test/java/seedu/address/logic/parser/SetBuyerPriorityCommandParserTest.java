package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPriorities.DEFAULT_PRIORITY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandWarnings;
import seedu.address.logic.commands.SetBuyerPriorityCommand;
import seedu.address.model.displayable.Priority;


public class SetBuyerPriorityCommandParserTest {
    private final SetBuyerPriorityCommandParser parser = new SetBuyerPriorityCommandParser();

    @Test
    public void parse_invalidArg_exceptionThrown() {
        assertParseFailure(parser, "1high",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBuyerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "high",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBuyerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "high 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBuyerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBuyerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBuyerPriorityCommand.MESSAGE_USAGE));
        assertThrows(NullPointerException.class, () -> parser.parse(null, new CommandWarnings()));
    }

    @Test
    public void parse_zeroInput_exceptionThrown() {
        assertParseFailure(parser, "0 nil",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBuyerPriorityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeInput_exceptionThrown() {
        assertParseFailure(parser, "-1 nil",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBuyerPriorityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonZeroUnsignedInteger_success() {
        assertParseSuccess(parser, "1 nil",
                new SetBuyerPriorityCommand(Index.fromZeroBased(0), DEFAULT_PRIORITY));

        assertParseSuccess(parser, "2 nil",
                new SetBuyerPriorityCommand(Index.fromZeroBased(1), DEFAULT_PRIORITY));

        assertParseSuccess(parser, "100 nil",
                new SetBuyerPriorityCommand(Index.fromZeroBased(99), DEFAULT_PRIORITY));
    }

    @Test
    public void parse_invalidPriority_exceptionThrown() {
        assertParseFailure(parser, "1 no",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBuyerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBuyerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBuyerPriorityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validPriority_success() {
        assertParseSuccess(parser, "1 high",
                new SetBuyerPriorityCommand(INDEX_FIRST_PERSON, new Priority("high")));

        assertParseSuccess(parser, "1 medium",
                new SetBuyerPriorityCommand(INDEX_FIRST_PERSON, new Priority("medium")));

        assertParseSuccess(parser, "1 med",
                new SetBuyerPriorityCommand(INDEX_FIRST_PERSON, new Priority("medium")));

        assertParseSuccess(parser, "1 low",
                new SetBuyerPriorityCommand(INDEX_FIRST_PERSON, new Priority("low")));

        assertParseSuccess(parser, "1 nil",
                new SetBuyerPriorityCommand(INDEX_FIRST_PERSON, new Priority("nil")));
    }
}
