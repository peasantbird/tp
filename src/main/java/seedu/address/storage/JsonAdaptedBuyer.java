package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.person.buyer.BuyHouseInfo;
import seedu.address.model.person.buyer.Buyer;
import java.util.List;
public class JsonAdaptedBuyer extends JsonAdaptedPerson {
    private final String buyerInfo;
    /**
     * Constructs a {@code JsonAdaptedBuyer} with the given buyer details.
     */
    @JsonCreator
    public JsonAdaptedBuyer(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("buyerInfo") String buyerInfo,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        super(name, phone, email, address, tags);
        this.buyerInfo = buyerInfo;
    }
    /**
     * Converts a given {@code Buyer} into this class for Jackson use.
     */
    public JsonAdaptedBuyer(Buyer source) {
        super(source);
        this.buyerInfo = source.getBuyHouseInfo().toString();
    }

    /**
     * Converts this Jackson-friendly adapted seller object into the model's {@code Buyer} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted seller.
     */
    public Buyer toModelType() throws IllegalValueException {
        Person p = super.toModelType();
        if (buyerInfo == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    BuyHouseInfo.class.getSimpleName()));
        }
        if (!BuyHouseInfo.isValidBuyHouseInfo(buyerInfo)) {
            throw new IllegalValueException(BuyHouseInfo.MESSAGE_CONSTRAINTS);
        }
        BuyHouseInfo buyerInfoModel = new BuyHouseInfo(buyerInfo);
        return new Buyer(p, buyerInfoModel);
    }
}
