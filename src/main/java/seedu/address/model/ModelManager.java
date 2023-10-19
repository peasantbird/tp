package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.buyer.Buyer;
import seedu.address.model.person.seller.Seller;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Buyer> filteredBuyers;

    private final FilteredList<Seller> filteredSellers;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredBuyers = new FilteredList<>(this.addressBook.getBuyerList());
        filteredSellers = new FilteredList<>(this.addressBook.getSellerList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getFilePath() {
        return userPrefs.getFilePath();
    }


    @Override
    public void setAddressBookFilePath(Path filePath) {
        requireAllNonNull(filePath);
        userPrefs.setAddressBookFilePath(filePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasBuyer(Buyer buyer) {
        requireNonNull(buyer);
        return addressBook.hasBuyer(buyer);
    }

    @Override
    public boolean hasSeller(Seller seller) {
        requireNonNull(seller);
        return addressBook.hasSeller(seller);
    }

    @Override
    public void deleteBuyer(Buyer targetBuyer) {
        addressBook.removeBuyer(targetBuyer);
    }

    @Override
    public void deleteSeller(Seller targetSeller) {
        addressBook.removeSeller(targetSeller);
    }

    @Override
    public void addBuyer(Buyer buyer) {
        addressBook.addBuyer(buyer);
        updateFilteredBuyerList(PREDICATE_SHOW_BUYERS);
    }

    @Override
    public void addSeller(Seller seller) {
        addressBook.addSeller(seller);
        updateFilteredSellerList(PREDICATE_SHOW_SELLERS);
    }

    @Override
    public void setBuyer(Buyer targetBuyer, Buyer editedBuyer) {
        requireAllNonNull(targetBuyer, editedBuyer);

        addressBook.setBuyer(targetBuyer, editedBuyer);
    }

    @Override
    public void setSeller(Seller targetSeller, Seller editedSeller) {
        requireAllNonNull(targetSeller, editedSeller);

        addressBook.setSeller(targetSeller, editedSeller);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Buyer} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Buyer> getFilteredBuyerList() {
        return filteredBuyers;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Buyer} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Seller> getFilteredSellerList() {
        return filteredSellers;
    }

    @Override
    public void updateFilteredBuyerList(Predicate<? super Buyer> predicate) {
        requireNonNull(predicate);
        filteredBuyers.setPredicate(predicate);
    }

    @Override
    public void updateFilteredSellerList(Predicate<? super Seller> predicate) {
        requireNonNull(predicate);
        filteredSellers.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredBuyers.equals(otherModelManager.filteredBuyers)
                && filteredSellers.equals(otherModelManager.filteredSellers);
    }

}
