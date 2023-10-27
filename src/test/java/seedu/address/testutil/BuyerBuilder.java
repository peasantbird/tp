package seedu.address.testutil;

import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Email;
import seedu.address.model.displayable.Info;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Phone;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.util.SampleDataUtil;

/**
 * Builds a buyer.
 */
public class BuyerBuilder extends PersonBuilder {
    public static final String DEFAULT_INFO = "Central Area 5 Room Condominium";

    private Info info;

    /**
     * Creates a {@code BuyerBuilder} with the default details.
     */
    public BuyerBuilder() {
        super();
        info = new Info(DEFAULT_INFO);
    }

    /**
     * Initializes the BuyerBuilder with the data of {@code buyerToCopy}.
     */
    public BuyerBuilder(Buyer buyerToCopy) {
        super(buyerToCopy);
        info = buyerToCopy.getInfo();
    }


    /**
     * Sets the {@code Name} of the {@code Buyer} that we are building.
     */
    @Override
    public BuyerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }
    /**
     * Sets the {@code Tags} of the {@code Buyer} that we are building.
     */
    @Override
    public BuyerBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }
    /**
     * Sets the {@code Address} of the {@code Buyer} that we are building.
     */
    @Override
    public BuyerBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }
    /**
     * Sets the {@code Phone} of the {@code Buyer} that we are building.
     */
    @Override
    public BuyerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Buyer} that we are building.
     */
    @Override
    public BuyerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Info} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withInfo(String info) {
        this.info = new Info(info);
        return this;
    }

    @Override
    public Buyer build() {
        return new Buyer(super.name, super.phone, super.email, super.address, info, super.tags);
    }
}
