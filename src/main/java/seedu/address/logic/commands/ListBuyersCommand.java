package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_BUYERS;

import seedu.address.model.Model;

/**
 * List all Buyers in the address book to the user.
 */
public class ListBuyersCommand extends Command {

    public static final String COMMAND_WORD = "blist";
    public static final String MESSAGE_SUCCESS = "Listed all buyers";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBuyerList(PREDICATE_SHOW_BUYERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
