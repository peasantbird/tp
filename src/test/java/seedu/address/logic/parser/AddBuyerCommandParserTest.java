package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.exceptions.ParseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.testutil.Assert.assertThrows;

public class AddBuyerCommandParserTest {
    static final String BROKEN_INPUT = "ALASDKJDL";
    static final String PARTIAL_INPUT = "n/adam p/3094 e/email@com ah/homeaddress";
    static final String BAD_FIELDS_INPUT = "n/adam p/badnumber e/email@com ah/homeaddress i/info";
    static final String VALID_INPUT ="n/adam p/3094 e/email@com ah/homeaddress i/info";
    AddBuyerCommandParser parser = new AddBuyerCommandParser();

    @Test
    public void assertFails_Parse_BrokenInput() {
        assertThrows(ParseException.class, () -> parser.parse(BROKEN_INPUT));
    }
    @Test
    public void assertFails_Parse_PartialInput() {
        assertThrows(ParseException.class, () -> parser.parse(PARTIAL_INPUT));
    }
    @Test
    public void assertFails_Parse_BadFieldsInput() {
        assertThrows(ParseException.class, () -> parser.parse(BAD_FIELDS_INPUT));
    }
    @Test
    public void assertPasses_Parse_BrokenInput() {
        assertDoesNotThrow(() -> parser.parse(VALID_INPUT));
    }
}
