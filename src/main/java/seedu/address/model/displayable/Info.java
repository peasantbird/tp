package seedu.address.model.displayable;

/**
 * Info about a certain displayable.
 */
public class Info {
    public static final String MESSAGE_CONSTRAINTS = "Info can take any values, and it should not be blank";

    public static final String MESSAGE_RECOMMENDATIONS = MESSAGE_CONSTRAINTS;

    /*
     * The first character of info must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */

    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final String AFFIRMATION_REGEX = VALIDATION_REGEX;

    //TODO: refactor BuyHouseInfo and SellHouseInfo into this class.
    private final String info;
    public Info(String info) {
        this.info = info;
    }

    public static boolean isAppropriateInfo(String test) {
        return test.matches(AFFIRMATION_REGEX);
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

    @Override
    public int hashCode() {
        return info.hashCode();
    }
}
