package seedu.address.testutil;

import seedu.address.model.person.buyer.BuyHouseInfo;
import seedu.address.model.person.buyer.Buyer;

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
     * Sets the {@code BuyHouseInfo} of the {@code Buyer} that we are building.
     */
    public PersonBuilder withBuyHouseInfo(String buyHouseInfo) {
        this.buyHouseInfo = new BuyHouseInfo(buyHouseInfo);
        return this;
    }

    @Override
    public Buyer build() {
        return new Buyer(super.name, super.phone, super.email, super.address, buyHouseInfo, super.tags);
    }
}
