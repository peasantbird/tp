package seedu.address.logic;

import java.util.Set;

/**
 * Encapsulates a warning message, containing potentially multiple warnings. Serves a purpose similar to exceptions,
 * but without terminating the current method.
 */
public class Warning {
    private Set<String> warnings;
    public Warning() {
    }
    public void addWarning(String message) {
        warnings.add(message);
    }

    public String getWarningMessage() {
        return warnings.toString();
    }
}
