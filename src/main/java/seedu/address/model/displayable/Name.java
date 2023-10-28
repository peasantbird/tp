package seedu.address.model.displayable;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.AppUtil;

import static seedu.address.commons.util.AppUtil.validateArgument;

/**
 * Represents a Displayable's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names cannot be blank";
    public static final String MESSAGE_RECOMMENDATIONS =
            "Names should contain only alphanumeric characters and spaces";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\S [\\s\\S]*";

    public static final String AFFIRMATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        AppUtil.validateArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static boolean isAppropriateName(String test) {
        return test.matches(AFFIRMATION_REGEX);
    }

    // TODO have a check when adding persons to use this fuzzy match to warn if the users seem similar.
    public boolean isSameNameFuzzyMatch(Name otherName) {
        String contentName = fullName.replaceAll(" ","");
        String contentOtherName = otherName.fullName.replaceAll(" ","");
        return contentName.equalsIgnoreCase(contentOtherName);
    }
    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        return fullName.equals(otherName.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
