package seedu.address.model.person.buyer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BuyHouseInfoTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BuyHouseInfo(null));
    }

    @Test
    public void constructor_invalidBuyHouseInfo_throwsIllegalArgumentException() {
        String invalidBuyHouseInfo = "";
        assertThrows(IllegalArgumentException.class, () -> new BuyHouseInfo(invalidBuyHouseInfo));
    }

    @Test
    public void isValidBuyHouseInfo() {
        // null info
        assertThrows(NullPointerException.class, () -> BuyHouseInfo.isValidBuyHouseInfo(null));

        // invalid info
        assertFalse(BuyHouseInfo.isValidBuyHouseInfo("")); // empty string
        assertFalse(BuyHouseInfo.isValidBuyHouseInfo(" ")); // spaces only

        // valid info
        assertTrue(BuyHouseInfo.isValidBuyHouseInfo("Nice view"));
        assertTrue(BuyHouseInfo.isValidBuyHouseInfo("-")); // one character
        assertTrue(BuyHouseInfo
                .isValidBuyHouseInfo("Leng Inc; 1234 Market St, San Francisco CA "
                        + "2349879; USA, very nice view")); // long address
    }

    @Test
    public void equals() {
        BuyHouseInfo buyHouseInfo = new BuyHouseInfo("Valid BuyHouseInfo");

        // same values -> returns true
        assertTrue(buyHouseInfo.equals(new BuyHouseInfo("Valid BuyHouseInfo")));

        // same object -> returns true
        assertTrue(buyHouseInfo.equals(buyHouseInfo));

        // null -> returns false
        assertFalse(buyHouseInfo.equals(null));

        // different types -> returns false
        assertFalse(buyHouseInfo.equals(5.0f));

        // different values -> returns false
        assertFalse(buyHouseInfo.equals(new BuyHouseInfo("Other Valid BuyHouseInfo")));
    }
}
