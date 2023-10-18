package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.util.Displayable;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the buyers list.
     * This list will not contain any duplicate buyers.
     */
    ObservableList<Displayable> getBuyerList();

    /**
     * Returns an unmodifiable view of the sellers list.
     * This list will not contain any duplicate sellers.
     */
    ObservableList<Displayable> getSellerList();

}
