package seedu.address.storage;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class to access AddressBook data stored as json files on the hard disk.
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path buyersPath;
    private Path sellersPath;

    /**
     * Constructs a JsonAddressBookStorage.
     * @param buyersPath the buyer path.
     * @param sellersPath the seller path.
     */
    public JsonAddressBookStorage(Path buyersPath, Path sellersPath) {
        this.buyersPath = buyersPath;
        this.sellersPath = sellersPath;
    }

    public Path getBuyersPath() {
        return buyersPath;
    }
    public Path getSellersPath() {
        return sellersPath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(buyersPath, sellersPath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param buyersPath location of the buyer data. Cannot be null.
     * @param sellersPath location of the seller data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path buyersPath, Path sellersPath)
            throws DataLoadingException {
        requireAllNonNull(buyersPath, sellersPath);
        Optional<JsonSerializableAddressBook> jsonBuyers = JsonUtil.readJsonFile(
                buyersPath, JsonSerializableAddressBook.class);
        Optional<JsonSerializableAddressBook> jsonAddressBookWithSellers = JsonUtil.readJsonFile(
                sellersPath, JsonSerializableAddressBook.class);
        if (!jsonBuyers.isPresent() && !jsonAddressBookWithSellers.isPresent()) {
            return Optional.empty();
        }
        AddressBook addressBook = new AddressBook();
        try {
            addressBook.setBuyers(jsonBuyers.get().toModelType().getBuyerList());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + buyersPath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
        try {
            addressBook.setSellers(jsonAddressBookWithSellers.get().toModelType().getSellerList());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + sellersPath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
        return Optional.of(addressBook);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, sellersPath, buyersPath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}.
     *
     * @param buyersPath location of the buyer data. Cannot be null.
     * @param sellersPath location of the seller data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path buyersPath, Path sellersPath) throws IOException {
        requireAllNonNull(addressBook, buyersPath, sellersPath);
        FileUtil.createIfMissing(buyersPath);
        FileUtil.createIfMissing(sellersPath);

        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(createAddressBookWithBuyers(addressBook)),
                buyersPath);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(createAddressBookWithSellers(addressBook)),
                sellersPath);
    }
    public AddressBook createAddressBookWithBuyers(ReadOnlyAddressBook addressBook) {
        AddressBook addressBookWithBuyers = new AddressBook();
        addressBookWithBuyers.setBuyers(addressBook.getBuyerList());
        return addressBookWithBuyers;
    }

    public AddressBook createAddressBookWithSellers(ReadOnlyAddressBook addressBook) {
        AddressBook addressBookWithSellers = new AddressBook();
        addressBookWithSellers.setSellers(addressBook.getSellerList());
        return addressBookWithSellers;
    }
}
