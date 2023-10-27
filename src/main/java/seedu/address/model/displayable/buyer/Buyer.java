package seedu.address.model.displayable.buyer;

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
import seedu.address.ui.BuyerCard;
import seedu.address.ui.UiPart;

/**
 * Represents a client looking to buy a house in the address book.
 */
public class Buyer extends Person {
    private final Info info;

    /**
     * Constructs a Buyer instance.
     *
     * @param name Name of the buyer.
     * @param phone Phone number of the buyer.
     * @param email Email of the buyer.
     * @param address Home address of the buyer.
     * @param info Information on the house that the buyer is interested in.
     * @param tags Optional tags.
     */
    public Buyer(Name name, Phone phone, Email email, Address address,
                 Info info, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        requireAllNonNull(info);
        this.info = info;
    }

    /**
     * Constructs a Buyer instance with Priority.
     * NOTE: This is a temporal method, to be refactored soon. Refer to explanation in
     * Person.java.
     *
     * @param name Name of the buyer.
     * @param phone Phone number of the buyer.
     * @param email Email of the buyer.
     * @param address Home address of the buyer.
     * @param info Information on the house that the buyer is interested in.
     * @param tags Optional tags.
     * @param priority Priority level of the buyer.
     */
    public Buyer(Name name, Phone phone, Email email, Address address,
                 Info info, Set<Tag> tags, Priority priority) {
        super(name, phone, email, address, tags, priority);
        requireAllNonNull(info);
        this.info = info;
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
        if (!(other instanceof Buyer)) {
            return false;
        }

        Buyer otherBuyer = (Buyer) other;
        return this.equalsHelper(otherBuyer)
                && info.equals(otherBuyer.info);
    }
    @Override
    public String toString() {
        return toStringBuild()
                .add("house info", info)
                .toString();
    }
    @Override
    public UiPart<Region> display(int displayIndex) {
        return new BuyerCard(this, displayIndex);
    }
}
