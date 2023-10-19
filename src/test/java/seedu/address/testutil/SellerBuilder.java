package seedu.address.testutil;


import seedu.address.model.person.Address;
import seedu.address.model.person.seller.SellHouseInfo;
import seedu.address.model.person.seller.Seller;

public class SellerBuilder extends PersonBuilder {

    public static final String DEFAULT_SELLING_ADDRESS = "47D Lor Sarhad, Singapore 119164";
    public static final String DEFAULT_SELL_HOUSE_INFO = "4 Room Flat in Sarhad Ville";

    private Address sellingAddress;
    private SellHouseInfo sellHouseInfo;

    /**
     * Creates a {@code SellerBuilder} with the default details.
     */
    public SellerBuilder() {
        super();
        sellingAddress = new Address(DEFAULT_SELLING_ADDRESS);
        sellHouseInfo = new SellHouseInfo(DEFAULT_SELL_HOUSE_INFO);
    }

    /**
     * Initializes the SellerBuilder with the data of {@code buyerToCopy}.
     */
    public SellerBuilder(Seller sellerToCopy) {
        super(sellerToCopy);
        sellingAddress = sellerToCopy.getSellingAddress();
        sellHouseInfo = sellerToCopy.getSellHouseInfo();
    }

    /**
     * Sets the {@code SellHouseInfo()} of the {@code Seller} that we are building.
     */
    public PersonBuilder withSellHouseInfo(String sellHouseInfo) {
        this.sellHouseInfo = new SellHouseInfo(sellHouseInfo);
        return this;
    }

    @Override
    public Seller build() {
        return new Seller(super.name, super.phone, super.email,
                super.address, sellingAddress, sellHouseInfo, super.tags);
    }
}
