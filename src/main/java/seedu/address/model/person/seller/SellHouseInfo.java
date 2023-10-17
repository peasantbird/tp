package seedu.address.model.person.seller;

/**
 * Represents the information associated with a house.
 */
public class SellHouseInfo {

    public static final String MESSAGE_CONSTRAINTS =
            "House information can take any values, and it should not be blank";
    /*
     * The first character of the info must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    private final String sellHouseInfo;
    public SellHouseInfo(String houseInfo) {
        this.sellHouseInfo = houseInfo;
    }
    public String toString() {
        return sellHouseInfo;
    }
    public static boolean isValidSellHouseInfo(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
