package seedu.address.model.displayable.seller;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import javafx.scene.layout.Region;
import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Email;
import seedu.address.model.displayable.Info;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Person;
import seedu.address.model.displayable.Phone;
import seedu.address.model.displayable.Priority;
import seedu.address.model.tag.Tag;
import seedu.address.ui.SellerCard;
import seedu.address.ui.UiPart;


/**
 * Represents a Seller in the address book.
 */
public class Seller extends Person {
    private final Address sellingAddress;
    private final Info info;

    /**
     * Constructs a seller instance.
     * Every field must be present and not null (super class does these checks too)
     *
     * @param name           name of the seller.
     * @param phone          phone number of the seller.
     * @param email          email of the seller.
     * @param address        the home address of the seller.
     * @param sellingAddress the address that the seller is listing.
     * @param info  info about the listing.
     * @param tags           tags of the seller.
     */
    public Seller(Name name, Phone phone, Email email, Address address, Address sellingAddress,
                  Info info, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        requireAllNonNull(sellingAddress, info);
        this.sellingAddress = sellingAddress;
        this.info = info;
    }

    /**
     * Constructs a seller instance.
     * NOTE: This overloaded constructor is temporal, to be refactored soon. Refer to Person.java
     * for full explanation.
     * Every field must be present and not null (super class does these checks too)
     *
     * @param name           name of the seller.
     * @param phone          phone number of the seller.
     * @param email          email of the seller.
     * @param address        the home address of the seller.
     * @param sellingAddress the address that the seller is listing.
     * @param info  info about the listing.
     * @param tags           tags of the seller.
     * @param priority       priority level of the seller.
     */
    public Seller(Name name, Phone phone, Email email, Address address, Address sellingAddress,
                  Info info, Set<Tag> tags, Priority priority) {
        super(name, phone, email, address, tags, priority);
        requireAllNonNull(sellingAddress, info);
        this.sellingAddress = sellingAddress;
        this.info = info;
    }

    public Address getSellingAddress() {
        return this.sellingAddress;
    }

    public Info getInfo() {
        return info;
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
                && info.equals(otherSeller.info);
    }
    @Override
    public String toString() {
        return toStringBuild()
                .add("selling address", sellingAddress)
                .add("house info", info)
                .toString();
    }
    @Override
    public UiPart<Region> display(int displayIndex) {
        return new SellerCard(this, displayIndex);
    }
}
