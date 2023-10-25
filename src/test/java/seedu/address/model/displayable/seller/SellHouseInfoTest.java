package seedu.address.model.displayable.seller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SellHouseInfoTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SellHouseInfo(null));
    }

    @Test
    public void constructor_invalidSellHouseInfo_throwsIllegalArgumentException() {
        String invalidBuyHouseInfo = "";
        assertThrows(IllegalArgumentException.class, () -> new SellHouseInfo(invalidBuyHouseInfo));
    }

    @Test
    public void isValidSellHouseInfo() {
        // null info
        assertThrows(NullPointerException.class, () -> SellHouseInfo.isValidSellHouseInfo(null));

        // invalid info
        assertFalse(SellHouseInfo.isValidSellHouseInfo("")); // empty string
        assertFalse(SellHouseInfo.isValidSellHouseInfo(" ")); // spaces only

        // valid info
        assertTrue(SellHouseInfo.isValidSellHouseInfo("Nice view"));
        assertTrue(SellHouseInfo.isValidSellHouseInfo("-")); // one character
        assertTrue(SellHouseInfo
                .isValidSellHouseInfo("Leng Inc; 1234 Market St, San Francisco CA "
                        + "2349879; USA, very nice view")); // long address
    }

    @Test
    public void equals() {
        SellHouseInfo sellHouseInfo = new SellHouseInfo("Valid SellHouseInfo");

        // same values -> returns true
        assertTrue(sellHouseInfo.equals(new SellHouseInfo("Valid SellHouseInfo")));

        // same object -> returns true
        assertTrue(sellHouseInfo.equals(sellHouseInfo));

        // null -> returns false
        assertFalse(sellHouseInfo.equals(null));

        // different types -> returns false
        assertFalse(sellHouseInfo.equals(5.0f));

        // different values -> returns false
        assertFalse(sellHouseInfo.equals(new SellHouseInfo("Other Valid SellHouseInfo")));
    }
}
