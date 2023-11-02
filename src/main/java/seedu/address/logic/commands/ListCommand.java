package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_BUYERS;
import static seedu.address.model.Model.PREDICATE_SHOW_SELLERS;

import seedu.address.model.Model;

/**
 * List all Buyers in the address book to the user.
 */
public class ListCommand extends Command {

<<<<<<< HEAD:src/main/java/seedu/address/logic/commands/ListCommand.java
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all buyers and sellers!";
=======
    public static final String COMMAND_WORD = "blist";
    public static final String MESSAGE_SUCCESS = "Listed all buyers";
>>>>>>> upstream/master:src/main/java/seedu/address/logic/commands/ListBuyersCommand.java

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBuyerList(PREDICATE_SHOW_BUYERS);
        model.updateFilteredSellerList(PREDICATE_SHOW_SELLERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
