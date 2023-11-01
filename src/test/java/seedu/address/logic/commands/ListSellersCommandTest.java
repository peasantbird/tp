package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showSellerAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.displayable.seller.Seller;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListSellerCommand.
 */
public class ListSellersCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Seller sellerToDisplay = model.getFilteredSellerList().get(INDEX_FIRST_PERSON.getZeroBased());
        ListSellerCommand listSellerCommand = new ListSellerCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ListSellerCommand.MESSAGE_LIST_SELLER_SUCCESS,
                Messages.format(sellerToDisplay));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(listSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSellerList().size() + 1);
        ListSellerCommand listSellerCommand = new ListSellerCommand(outOfBoundIndex);

        assertCommandFailure(listSellerCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showSellerAtIndex(model, INDEX_FIRST_PERSON);

        Seller sellerToDisplay = model.getFilteredSellerList().get(INDEX_FIRST_PERSON.getZeroBased());
        ListSellerCommand listSellerCommand = new ListSellerCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ListSellerCommand.MESSAGE_LIST_SELLER_SUCCESS,
                Messages.format(sellerToDisplay));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showSellerAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(listSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showSellerAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getSellerList().size());

        ListSellerCommand listSellerCommand = new ListSellerCommand(outOfBoundIndex);

        assertCommandFailure(listSellerCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }
}
