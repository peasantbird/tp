package seedu.address.logic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class CommandWarningsTest {

    private static final String TEST1 = "Testwithoutspaces";
    private static final String TEST2 = "   whitespaced   ";
    private static final String TEST3 = "Sting with spaces";
    private static final String TEST4 = "String with unusual characters 中文 (*9328^%92 *~/- :F:f;";
    private static final String BLANK = "";
    private static final String BLANK2 = "    ";
    @Test
    public void warnings_addTest() {
        CommandWarnings warnings = new CommandWarnings();
        warnings.addWarning(TEST1);
        assertTrue(warnings.containsWarningString(TEST1));
        warnings.addWarning(TEST2);
        assertTrue(warnings.containsWarningString(TEST2));
        warnings.addWarning(TEST3);
        assertTrue(warnings.containsWarningString(TEST3));
        warnings.addWarning(TEST4);
        assertTrue(warnings.containsWarningString(TEST4));
        warnings.addWarning(BLANK);
        warnings.addWarning(BLANK2);
        assertTrue(warnings.containsWarningString(TEST1));
        assertTrue(warnings.containsWarningString(TEST2));
        assertTrue(warnings.containsWarningString(TEST3));
        assertTrue(warnings.containsWarningString(TEST4));
        assertTrue(warnings.containsWarningString(BLANK));
        assertTrue(warnings.containsWarningString(BLANK2));
    }
    @Test
    public void warnings_getWarningMessage() {
        CommandWarnings warnings = new CommandWarnings();
        warnings.addWarning(TEST1);
        assertEquals(warnings.getWarningMessage(),
                "Warning!; [" + TEST1 + "]\nPlease ignore if this is expected.");
        warnings.addWarning(TEST4);
        assertEquals(warnings.getWarningMessage(),
                "Warning!; [" + TEST1 + ", " + TEST4 + "]\nPlease ignore if this is expected.");
    }
    @Test
    public void warnings_equality() {
        CommandWarnings warnings = new CommandWarnings();
        warnings.addWarning("Test");
        CommandWarnings other = new CommandWarnings();
        assertFalse(warnings.equals(other));
        other.addWarning("Test");
        assertTrue(warnings.equals(other));
    }
}
