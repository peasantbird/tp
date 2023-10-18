package seedu.address.model.person.seller;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import javafx.scene.layout.Region;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.ui.SellerCard;
import seedu.address.ui.UiPart;


/**
 * Represents a Seller in the address book.
 */
public class Seller extends Person {
    private final Address sellingAddress;
    private final SellHouseInfo sellHouseInfo;

    /**
     * Constructs a seller instance.
     * Every field must be present and not null (super class does these checks too)
     *
     * @param name           name of the seller.
     * @param phone          phone number of the seller.
     * @param email          email of the seller.
     * @param address        the home address of the seller.
     * @param sellingAddress the address that the seller is listing.
     * @param sellHouseInfo  info about the listing.
     * @param tags           tags of the seller.
     */
    public Seller(Name name, Phone phone, Email email, Address address, Address sellingAddress,
                  SellHouseInfo sellHouseInfo, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        requireAllNonNull(sellingAddress, sellHouseInfo);
        this.sellingAddress = sellingAddress;
        this.sellHouseInfo = sellHouseInfo;
    }

    public Address getSellingAddress() {
        return this.sellingAddress;
    }

    public SellHouseInfo getSellHouseInfo() {
        return sellHouseInfo;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Seller)) {
            return false;
        }

        Seller otherSeller = (Seller) other;
        return this.equalsHelper(otherSeller)
                && sellingAddress.equals(otherSeller.sellingAddress)
                && sellHouseInfo.equals(otherSeller.sellHouseInfo);
    }
    @Override
    public String toString() {
        return toStringBuild()
                .add("selling address", sellingAddress)
                .add("house info", sellHouseInfo)
                .toString();
    }
    @Override
    public UiPart<Region> display(int displayIndex) {
        return new SellerCard(this, displayIndex);
    }
}
