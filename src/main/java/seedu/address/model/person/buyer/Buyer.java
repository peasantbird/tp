package seedu.address.model.person.buyer;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents a client looking to buy a house in the address book.
 */
public class Buyer extends Person {
    private final BuyHouseInfo buyHouseInfo;

    /**
     * Constructs a Buyer instance.
     *
     * @param name Name of the buyer.
     * @param phone Phone number of the buyer.
     * @param email Email of the buyer.
     * @param address Home address of the buyer.
     * @param buyHouseInfo Information on the house that the buyer is interested in.
     * @param tags Optional tags.
     */
    public Buyer(Name name, Phone phone, Email email, Address address,
                 BuyHouseInfo buyHouseInfo, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        requireAllNonNull(buyHouseInfo);
        this.buyHouseInfo = buyHouseInfo;
    }

    public Buyer(Person person, BuyHouseInfo buyHouseInfo) {
        this(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(),
                buyHouseInfo, person.getTags());
    }
    public BuyHouseInfo getBuyHouseInfo() {
        return buyHouseInfo;
    }
}
