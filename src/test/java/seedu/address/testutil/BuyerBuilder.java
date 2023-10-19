package seedu.address.testutil;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.buyer.BuyHouseInfo;
import seedu.address.model.person.buyer.Buyer;
import seedu.address.model.util.SampleDataUtil;

/**
 * Builds a buyer.
 */
public class BuyerBuilder extends PersonBuilder {
    public static final String DEFAULT_BUY_HOUSE_INFO = "Central Area 5 Room Condominium";

    private BuyHouseInfo buyHouseInfo;

    /**
     * Creates a {@code BuyerBuilder} with the default details.
     */
    public BuyerBuilder() {
        super();
        buyHouseInfo = new BuyHouseInfo(DEFAULT_BUY_HOUSE_INFO);
    }

    /**
     * Initializes the BuyerBuilder with the data of {@code buyerToCopy}.
     */
    public BuyerBuilder(Buyer buyerToCopy) {
        super(buyerToCopy);
        buyHouseInfo = buyerToCopy.getBuyHouseInfo();
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
     * Sets the {@code BuyHouseInfo} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withBuyHouseInfo(String buyHouseInfo) {
        this.buyHouseInfo = new BuyHouseInfo(buyHouseInfo);
        return this;
    }

    @Override
    public Buyer build() {
        return new Buyer(super.name, super.phone, super.email, super.address, buyHouseInfo, super.tags);
    }
}
