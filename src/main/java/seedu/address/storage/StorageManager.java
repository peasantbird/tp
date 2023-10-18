package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getBuyersPath() {
        return addressBookStorage.getBuyersPath();
    }
    @Override
    public Path getSellersPath() {
        return addressBookStorage.getSellersPath();
    }
    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(addressBookStorage.getBuyersPath(),addressBookStorage.getSellersPath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path buyersPath, Path sellersPath) throws DataLoadingException {
        logger.fine("Attempting to read data from files: " + buyersPath + ", " + sellersPath);
        return addressBookStorage.readAddressBook(buyersPath, sellersPath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getBuyersPath(), addressBookStorage.getSellersPath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path buyersPath, Path sellersPath) throws IOException {
        logger.fine("Attempting to write to data file: " + buyersPath + ", " + sellersPath);
        addressBookStorage.saveAddressBook(addressBook, buyersPath, sellersPath);
    }

}
