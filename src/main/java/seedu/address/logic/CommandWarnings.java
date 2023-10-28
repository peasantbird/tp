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

    public boolean containsWarningString(String warning) {
        return warnings.contains(warning);
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
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandWarnings)) {
            return false;
        }

        CommandWarnings otherCommandWarnings = (CommandWarnings) other;
        return warnings.equals(otherCommandWarnings.warnings);
    }
    @Override
    public String toString() {
        return warnings.toString();
    }
}
