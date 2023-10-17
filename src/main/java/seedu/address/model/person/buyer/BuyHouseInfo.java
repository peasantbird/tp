package seedu.address.model.person.buyer;

/**
 * Represents the information associated with a house.
 */
public class BuyHouseInfo {

    public static final String MESSAGE_CONSTRAINTS =
            "House information can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String houseInfo;

    public BuyHouseInfo(String houseInfo) {
        this.houseInfo = houseInfo;
    }

    /**
     * Returns true if a given string is a valid house information.
     */
    public static boolean isValidHouseInfo(String test) {
        return test.matches(VALIDATION_REGEX);
    }

}
