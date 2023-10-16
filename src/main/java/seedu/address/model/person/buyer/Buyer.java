package seedu.address.model.person.buyer;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a client looking to buy a house in the address book.
 */
public class Buyer extends Person {
    private final HouseInfo houseInfo;

    public Buyer(Name name, Phone phone, Email email, Address address,
                 HouseInfo houseInfo, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        requireAllNonNull(houseInfo);
        this.houseInfo = houseInfo;
    }
}
