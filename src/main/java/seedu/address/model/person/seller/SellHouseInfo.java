package seedu.address.model.person.seller;

/**
 * Represents the information associated with a house.
 */
public class SellHouseInfo {

    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final String MESSAGE_CONSTRAINTS = "House information can take any value, and it should not be blank";

    private final String sellHouseInfo;
    public SellHouseInfo(String houseInfo) {
        this.sellHouseInfo = houseInfo;
    }

    /**
     * Returns true if a given string is valid house information.
     */
    public static boolean isValidHouseInfo(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
