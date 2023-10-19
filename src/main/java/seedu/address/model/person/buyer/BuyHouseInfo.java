package seedu.address.model.person.buyer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the information associated with a house.
 */
public class BuyHouseInfo {

    public static final String MESSAGE_CONSTRAINTS =
            "House information can take any values, and it should not be blank";
    /*
    * The first character of the info must not be a whitespace,
    * otherwise " " (a blank string) becomes a valid input.
    */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    private final String buyHouseInfo;

    public BuyHouseInfo(String buyHouseInfo) {
        requireNonNull(buyHouseInfo);
        checkArgument(isValidBuyHouseInfo(buyHouseInfo), MESSAGE_CONSTRAINTS);
        this.buyHouseInfo = buyHouseInfo;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BuyHouseInfo)) {
            return false;
        }

        BuyHouseInfo otherBuyHouseInfo = (BuyHouseInfo) other;
        return this.buyHouseInfo.equals(otherBuyHouseInfo.buyHouseInfo);
    }

    public String toString() {
        return buyHouseInfo;
    }
    public static boolean isValidBuyHouseInfo(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
