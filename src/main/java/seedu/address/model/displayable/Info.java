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

    @Override
    public String toString() {
        return info;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Info)) {
            return false;
        }

        Info otherInfo = (Info) other;
        return info.equals(otherInfo.info);
    }

    @Override
    public int hashCode() {
        return info.hashCode();
    }
}
