package seedu.address.model.displayable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {

    private static final Priority priority0 = new Priority("high");
    private static final Priority priority1 = new Priority("medium");
    private static final Priority priority2 = new Priority("low");
    private static final Priority priority3 = new Priority("nil");
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority(null));
    }

    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "1";
        assertThrows(IllegalArgumentException.class, () -> new Priority(invalidPriority));
    }

    @Test
    public void getPrioLvl_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Priority.getPrioLvl(null));
    }

    @Test
    public void getPrioLvl_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "1";
        assertThrows(IllegalArgumentException.class, () -> Priority.getPrioLvl(invalidPriority));
    }

    @Test
    public void getPrioLvl_validPriority_returnsCorrectEnumVal() {
        assertEquals(Priority.PrioLvl.HIGH, Priority.getPrioLvl("high"));
        assertEquals(Priority.PrioLvl.MEDIUM, Priority.getPrioLvl("medium"));
        assertEquals(Priority.PrioLvl.MEDIUM, Priority.getPrioLvl("med"));
        assertEquals(Priority.PrioLvl.LOW, Priority.getPrioLvl("low"));
        assertEquals(Priority.PrioLvl.NIL, Priority.getPrioLvl("nil"));
    }

    @Test
    public void isValidPriority() {
        // null priority
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // invalid priority levels
        assertFalse(Priority.isValidPriority("")); // empty string
        assertTrue(Priority.isValidPriority("   ")); // spaces only
        assertFalse(Priority.isValidPriority("testing")); // invalid arguments
        assertFalse(Priority.isValidPriority("0")); // non-alphabetical
        assertFalse(Priority.isValidPriority("123")); // non-alphabetical

        // inappropriate priority levels
        assertTrue(Priority.isValidPriority("h"));
        assertTrue(Priority.isValidPriority("m"));
        assertTrue(Priority.isValidPriority("l"));
        assertTrue(Priority.isValidPriority("hig"));
        assertTrue(Priority.isValidPriority("h9gh"));
        assertTrue(Priority.isValidPriority("med ium"));
        assertTrue(Priority.isValidPriority("lo"));

        // valid priority levels
        assertTrue(Priority.isValidPriority("High"));
        assertTrue(Priority.isValidPriority("high"));
        assertTrue(Priority.isValidPriority("medium"));
        assertTrue(Priority.isValidPriority("med"));
        assertTrue(Priority.isValidPriority("medim"));
        assertTrue(Priority.isValidPriority("low"));
        assertTrue(Priority.isValidPriority("nil"));
        assertTrue(Priority.isValidPriority("NIL"));
    }
    @Test
    public void isAppropriatePriority() {
        assertFalse(Priority.isAppropriatePriority("")); // empty string
        assertFalse(Priority.isAppropriatePriority("   ")); // spaces only
        assertFalse(Priority.isAppropriatePriority("testing")); // invalid arguments
        assertFalse(Priority.isAppropriatePriority("0")); // non-alphabetical
        assertFalse(Priority.isAppropriatePriority("123")); // non-alphabetical
        assertFalse(Priority.isAppropriatePriority("h9gh"));
        assertFalse(Priority.isAppropriatePriority("med ium"));

        assertTrue(Priority.isAppropriatePriority("h"));
        assertTrue(Priority.isAppropriatePriority("m"));
        assertTrue(Priority.isAppropriatePriority("l"));
        assertTrue(Priority.isAppropriatePriority("hig")); // typos
        assertTrue(Priority.isAppropriatePriority("lo"));

        assertTrue(Priority.isAppropriatePriority("high"));
        assertTrue(Priority.isAppropriatePriority("medium"));
        assertTrue(Priority.isAppropriatePriority("med"));
        assertTrue(Priority.isAppropriatePriority("medim"));
        assertTrue(Priority.isAppropriatePriority("low"));
        assertTrue(Priority.isAppropriatePriority("nil"));
        assertTrue(Priority.isAppropriatePriority("NIL"));
    }

    @Test
    public void toString_returnsCorrectOutput() {
        assertEquals("high", priority0.toString());
        assertEquals("med", priority1.toString());
        assertEquals("low", priority2.toString());
        assertEquals("nil", priority3.toString());
    }

    @Test
    public void getBackgroundColor_returnsCorrectOutput() {
        assertEquals("red", priority0.getBackgroundColor());
        assertEquals("orange", priority1.getBackgroundColor());
        assertEquals("green", priority2.getBackgroundColor());
        assertEquals("purple", priority3.getBackgroundColor());
    }

    @Test
    public void isPriorityNil_checksCorrectly() {
        assertTrue(priority3.isPriorityNil());
        assertFalse(priority2.isPriorityNil());
        assertFalse(priority0.isPriorityNil());
    }

    @Test
    public void equals() {
        Priority priority = new Priority("medium");

        // same values -> returns true
        assertTrue(priority.equals(new Priority("medium")));

        // same object -> returns true
        assertTrue(priority.equals(priority));

        // null -> returns false
        assertFalse(priority.equals(null));

        // different types -> returns false
        assertFalse(priority.equals("priority"));

        // different values -> returns false
        assertFalse(priority.equals(new Priority("high")));
    }
}
