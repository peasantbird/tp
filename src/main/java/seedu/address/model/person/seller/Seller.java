package seedu.address.model.person.seller;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.Set;

public class Seller extends Person {
    Address sellingAddress;
    HouseInfo houseInfo;
    public Seller(Name name, Phone phone, Email email, Address address, Address sellingAddress, HouseInfo houseInfo, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.sellingAddress = sellingAddress;
        this.houseInfo = houseInfo;
    }
}
