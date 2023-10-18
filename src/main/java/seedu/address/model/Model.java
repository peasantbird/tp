package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.person.buyer.Buyer;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.util.Displayable;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that evaluates to true only for sellers */
    Predicate<Displayable> PREDICATE_SHOW_SELLERS = i -> i instanceof Seller;

    /** {@code Predicate} that evaluates to true only for buyers */
    Predicate<Displayable> PREDICATE_SHOW_BUYERS = i -> i instanceof Buyer;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a buyer with the same identity as {@code buyer} exists in the address book's buyer list.
     */
    boolean hasBuyer(Person buyer);

    /**
     * Returns true if a seller with the same identity as {@code seller} exists in the address book's seller list.
     */
    boolean hasSeller(Person seller);

    /**
     * Deletes the given buyer.
     * The buyer must exist in the address book's buyer list.
     */
    void deleteBuyer(Person targetBuyer);

    /**
     * Deletes the given seller.
     * The seller must exist in the address book's seller list.
     */
    void deleteSeller(Person targetSeller);

    /**
     * Adds the given buyer.
     * {@code buyer} must not already exist in the address book's buyer list.
     */
    void addBuyer(Person buyer);

    /**
     * Adds the given seller.
     * {@code seller} must not already exist in the address book's seller list.
     */
    void addSeller(Person seller);

    /**
     * Replaces the given buyer {@code targetBuyer} with {@code editedBuyer}.
     * {@code targetBuyer} must exist in the address book's buyer list.
     * The person identity of {@code editedBuyer} must not be the same as another existing buyer in the buyer list.
     */
    void setBuyer(Person targetBuyer, Person editedBuyer);

    /**
     * Replaces the given seller {@code targetSeller} with {@code editedSeller}.
     * {@code targetSeller} must exist in the address book's seller list.
     * The person identity of {@code editedSeller} must not be the same as another existing seller in the seller list.
     */
    void setSeller(Person targetSeller, Person editedSeller);

    /** Returns an unmodifiable view of the filtered buyer list */
    ObservableList<Displayable> getFilteredBuyerList();

    /** Returns an unmodifiable view of the filtered seller list */
    ObservableList<Displayable> getFilteredSellerList();

    /**
     * Updates the filter of the filtered buyer list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBuyerList(Predicate<Displayable> predicate);

    /**
     * Updates the filter of the filtered seller list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredSellerList(Predicate<Displayable> predicate);
}
