package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPriorities.HIGH_PRIORITY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.logic.commands.AddSellerCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteBuyerCommand;
import seedu.address.logic.commands.DeleteSellerCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListBuyersCommand;
import seedu.address.logic.commands.ListSellersCommand;
import seedu.address.logic.commands.SetBuyerPriorityCommand;
import seedu.address.logic.commands.SetSellerPriorityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.displayable.seller.Seller;
import seedu.address.testutil.BuyerBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.SellerBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addBuyer() throws Exception {
        Buyer buyer = new BuyerBuilder().build();
        AddBuyerCommand command = (AddBuyerCommand) parser.parseCommand(PersonUtil.getAddCommand(buyer));
        assertEquals(new AddBuyerCommand(buyer), command);
    }

    @Test
    public void parseCommand_addSeller() throws Exception {
        Seller seller = new SellerBuilder().build();
        AddSellerCommand command = (AddSellerCommand) parser.parseCommand(PersonUtil.getAddCommand(seller));
        assertEquals(new AddSellerCommand(seller), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteBuyer() throws Exception {
        DeleteBuyerCommand command = (DeleteBuyerCommand) parser.parseCommand(
                DeleteBuyerCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteBuyerCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteSeller() throws Exception {
        DeleteSellerCommand command = (DeleteSellerCommand) parser.parseCommand(
                DeleteSellerCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteSellerCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listBuyers() throws Exception {
        assertTrue(parser.parseCommand(ListBuyersCommand.COMMAND_WORD) instanceof ListBuyersCommand);
        assertTrue(parser.parseCommand(ListBuyersCommand.COMMAND_WORD + " 3") instanceof ListBuyersCommand);
    }

    @Test
    public void parseCommand_listSellers() throws Exception {
        assertTrue(parser.parseCommand(ListSellersCommand.COMMAND_WORD) instanceof ListSellersCommand);
        assertTrue(parser.parseCommand(ListSellersCommand.COMMAND_WORD + " 3") instanceof ListSellersCommand);
    }

    @Test
    public void parseCommand_setBuyerPriority() throws Exception {
        SetBuyerPriorityCommand command = (SetBuyerPriorityCommand) parser.parseCommand(
                SetBuyerPriorityCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_PERSON.getOneBased() + " "
                        + HIGH_PRIORITY);
        assertEquals(new SetBuyerPriorityCommand(INDEX_FIRST_PERSON, HIGH_PRIORITY), command);
    }

    @Test
    public void parseCommand_setSellerPriority() throws Exception {
        SetSellerPriorityCommand command = (SetSellerPriorityCommand) parser.parseCommand(
                SetSellerPriorityCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_PERSON.getOneBased() + " "
                        + HIGH_PRIORITY);
        assertEquals(new SetSellerPriorityCommand(INDEX_FIRST_PERSON, HIGH_PRIORITY), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
