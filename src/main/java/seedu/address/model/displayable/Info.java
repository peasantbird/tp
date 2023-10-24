package seedu.address.model.displayable;

/**
 * Info about a certain displayable.
 */
public class Info {
    //TODO: refactor BuyHouseInfo and SellHouseInfo into this class.
    private final String info;
    public Info(String info) {
        this.info = info;
    }

    public String toString() {
        return info;
    }
}
