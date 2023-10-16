package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_SELLERS;

import seedu.address.model.Model;

/**
 * Lists all sellers in the address book to the user.
 */
public class ListSellersCommand extends Command {

    public static final String COMMAND_WORD = "list-s";
    public static final String MESSAGE_SUCCESS = "Listed all sellers";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_SELLERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
