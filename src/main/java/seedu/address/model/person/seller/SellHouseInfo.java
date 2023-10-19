package seedu.address.model.person.seller;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

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

    /**
     * Constructs a SellHouseInfo.
     * @param houseInfo info about a house.
     */
    public SellHouseInfo(String houseInfo) {
        requireNonNull(houseInfo);
        checkArgument(isValidSellHouseInfo(houseInfo), MESSAGE_CONSTRAINTS);
        this.sellHouseInfo = houseInfo;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SellHouseInfo)) {
            return false;
        }

        SellHouseInfo otherSellHouseInfo = (SellHouseInfo) other;
        return this.sellHouseInfo.equals(otherSellHouseInfo.sellHouseInfo);
    }

    public String toString() {
        return sellHouseInfo;
    }
    public static boolean isValidSellHouseInfo(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
