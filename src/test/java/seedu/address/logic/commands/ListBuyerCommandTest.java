package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBuyerAtIndex;
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
import seedu.address.model.displayable.buyer.Buyer;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListBuyerCommand.
 */
public class ListBuyerCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Buyer buyerToDisplay = model.getFilteredBuyerList().get(INDEX_FIRST_PERSON.getZeroBased());
        ListBuyerCommand listBuyerCommand = new ListBuyerCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ListBuyerCommand.MESSAGE_LIST_BUYER_SUCCESS,
                Messages.format(buyerToDisplay));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(listBuyerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBuyerList().size() + 1);
        ListBuyerCommand listBuyerCommand = new ListBuyerCommand(outOfBoundIndex);

        assertCommandFailure(listBuyerCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showBuyerAtIndex(model, INDEX_FIRST_PERSON);

        Buyer buyerToDisplay = model.getFilteredBuyerList().get(INDEX_FIRST_PERSON.getZeroBased());
        ListBuyerCommand listBuyerCommand = new ListBuyerCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ListBuyerCommand.MESSAGE_LIST_BUYER_SUCCESS,
                Messages.format(buyerToDisplay));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showBuyerAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(listBuyerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBuyerAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getBuyerList().size());

        ListBuyerCommand listBuyerCommand = new ListBuyerCommand(outOfBoundIndex);

        assertCommandFailure(listBuyerCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }
}
