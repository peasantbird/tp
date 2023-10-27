package seedu.address.model.displayable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InfoTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Info(null));
    }

    @Test
    public void constructor_invalidInfo_throwsIllegalArgumentException() {
        String invalidInfo = "";
        assertThrows(IllegalArgumentException.class, () -> new Info(invalidInfo));
    }

    @Test
    public void isValidInfo() {
        // null info
        assertThrows(NullPointerException.class, () -> Info.isValidInfo(null));

        // invalid info
        assertFalse(Info.isValidInfo("")); // empty string
        assertFalse(Info.isValidInfo(" ")); // spaces only

        // valid info
        assertTrue(Info.isValidInfo("Nice view"));
        assertTrue(Info.isValidInfo("-")); // one character
        assertTrue(Info
                .isValidInfo("Leng Inc; 1234 Market St, San Francisco CA "
                        + "2349879; USA, very nice view")); // long address
    }

    @Test
    public void equals() {
        Info info = new Info("Valid info");
        // same values -> returns true
        assertTrue(info.equals(new Info("Valid info")));

        // same object -> returns true
        assertTrue(info.equals(info));

        // null -> returns false
        assertFalse(info.equals(null));

        // different types -> returns false
        assertFalse(info.equals(5.0f));

        // different values -> returns false
        assertFalse(info.equals(new Info("Other Valid ")));
    }
}
