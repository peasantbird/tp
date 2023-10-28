package seedu.address.logic;

import java.util.HashSet;
import java.util.Set;

/**
 * Encapsulates a set of warning messages, Serves a purpose similar to exceptions,
 * but without terminating the current method.
 */
public class CommandWarnings {
    private final Set<String> warnings;
    public CommandWarnings() {
        this.warnings = new HashSet<>();
    }
    public void addWarning(String message) {
        warnings.add(message);
    }
    public boolean containsWarnings() {
        return !(warnings.isEmpty());
    }

    public String getWarningMessage() {
        if (warnings.isEmpty()) {
            return "";
        }
        return "Warning!; " + warnings + " Please ignore if this is expected.";
    }
    public String toString() {
        return warnings.toString();
    }
}
