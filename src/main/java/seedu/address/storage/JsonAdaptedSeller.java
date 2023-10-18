package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.seller.SellHouseInfo;
import seedu.address.model.person.seller.Seller;
/**
 * Jackson-friendly version of {@link Seller}.
 */
public class JsonAdaptedSeller extends JsonAdaptedPerson {
    private final String sellerInfo;
    private final String sellingAddress;
    /**
     * Constructs a {@code JsonAdaptedSeller} with the given seller details.
     */
    @JsonCreator
    public JsonAdaptedSeller(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                            @JsonProperty("email") String email, @JsonProperty("address") String address,
                            @JsonProperty("sellingAddress") String sellingAddress,
                            @JsonProperty("buyerInfo") String sellerInfo,
                            @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        super(name, phone, email, address, tags);
        this.sellerInfo = sellerInfo;
        this.sellingAddress = sellingAddress;
    }
    /**
     * Converts a given {@code Seller} into this class for Jackson use.
     */
    public JsonAdaptedSeller(Seller source) {
        super(source);
        this.sellingAddress = source.getSellingAddress().value;
        this.sellerInfo = source.getSellHouseInfo().toString();
    }

    /**
     * Converts this Jackson-friendly adapted seller object into the model's {@code Seller} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted seller.
     */
    public Seller toModelType() throws IllegalValueException {
        if (sellerInfo == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    SellHouseInfo.class.getSimpleName()));
        }
        if (!SellHouseInfo.isValidSellHouseInfo(sellerInfo)) {
            throw new IllegalValueException(SellHouseInfo.MESSAGE_CONSTRAINTS);
        }
        SellHouseInfo sellerInfoModel = new SellHouseInfo(sellerInfo);
        if (sellingAddress == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(sellingAddress)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        Address sellerAddressModel = new Address(sellingAddress);
        return new Seller(getName(), getPhone(), getEmail(), getAddress(), sellerAddressModel,
                sellerInfoModel, getTags());
    }
}
