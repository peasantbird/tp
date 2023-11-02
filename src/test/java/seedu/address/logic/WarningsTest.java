package seedu.address.logic;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class WarningsTest {

    @Test
    public void warnings_addTest() {
        CommandWarnings warnings = new CommandWarnings();
        warnings.addWarning("Test");
        assertTrue(warnings.containsWarningString("Test"));
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
