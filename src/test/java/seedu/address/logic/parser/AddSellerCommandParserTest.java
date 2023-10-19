package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddSellerCommand;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class AddSellerCommandParserTest {
    static final String BROKEN_INPUT = "ALASDKJDL";
    static final String PARTIAL_INPUT = "n/adam p/3094 e/email@com ah/homeaddress";
    static final String BAD_FIELDS_INPUT = "n/adam p/badnumber e/email@com ah/homeaddress as/selladdress i/info";

    //TODO: Make valid input equivalent to ALICE once added.
    static final String VALID_INPUT ="n/adam p/3094 e/email@com ah/homeaddress as/selladdress i/info";

    AddSellerCommandParser parser = new AddSellerCommandParser();

    @Test
    public void assertFails_Parse_BrokenInput() {
        assertParseFailure(parser, BROKEN_INPUT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSellerCommand.MESSAGE_USAGE));
    }
    @Test
    public void assertFails_Parse_PartialInput() {
        assertParseFailure(parser, PARTIAL_INPUT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSellerCommand.MESSAGE_USAGE));
    }
    @Test
    public void assertFails_Parse_BadFieldsInput() {
        assertParseFailure(parser, BAD_FIELDS_INPUT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSellerCommand.MESSAGE_USAGE));
    }
    @Test
    public void assertPasses_Parse_BrokenInput() {
        assertParseSuccess(parser, VALID_INPUT, new AddSellerCommand(ALICE));
    }
}