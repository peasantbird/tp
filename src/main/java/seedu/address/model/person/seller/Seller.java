package seedu.address.model.person.seller;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;



/**
 * Represents a Seller in the address book.
 */
public class Seller extends Person {
    private final Address sellingAddress;
    private final SellHouseInfo sellHouseInfo;
    /**
     * Every field must be present and not null (super class does these checks too)
     */
    public Seller(Name name, Phone phone, Email email, Address address, Address sellingAddress,
                  SellHouseInfo sellHouseInfo, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        requireAllNonNull(sellingAddress, sellHouseInfo);
        this.sellingAddress = sellingAddress;
        this.sellHouseInfo = sellHouseInfo;
    }
    public Seller(Person person, Address sellingAddress, SellHouseInfo sellHouseInfo) {
        this(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(),
                sellingAddress, sellHouseInfo, person.getTags());
    }
    public Address getSellingAddress() {
        return this.sellingAddress;
    }
    public SellHouseInfo getSellHouseInfo() {
        return sellHouseInfo;
    }
}
