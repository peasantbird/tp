package seedu.address.model.displayable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Info about a certain displayable.
 */
public class Info {
    public static final String MESSAGE_CONSTRAINTS =
        "House information can take any values, and it should not be blank";
    /*
     * The first character of the info must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    private final String info;
    public Info(String info) {
        requireNonNull(info);
        checkArgument(isValidInfo(info), MESSAGE_CONSTRAINTS);
        this.info = info;
    }

    @Override
    public String toString() {
        return info;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Info)) {
            return false;
        }

        Info otherInfo = (Info) other;
        return info.equals(otherInfo.info);
    }
    public static boolean isValidInfo(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    @Override
    public int hashCode() {
        return info.hashCode();
    }
}
