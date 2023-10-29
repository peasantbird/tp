package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

//@@author peasantbird-reused
//Reused from Address Book (Level4) with minor modifications
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Last command was undone";
    public static final String MESSAGE_FAILURE = "No commands to undo";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoAddressBook()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undoAddressBook();
        model.updateFilteredBuyerList(Model.PREDICATE_SHOW_BUYERS);
        model.updateFilteredSellerList(Model.PREDICATE_SHOW_SELLERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
//@@author
