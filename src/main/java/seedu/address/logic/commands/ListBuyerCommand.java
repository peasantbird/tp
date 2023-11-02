package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.displayable.buyer.Buyer;

/**
 * Displays information of a specified buyer.
 */
public class ListBuyerCommand extends Command {

    public static final String COMMAND_WORD = "blist";
    public static final String MESSAGE_LIST_BUYER_SUCCESS = "Got it. Here's the information of this buyer:\n%1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays information of the buyer identified by the index number used in the displayed buyer list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    public ListBuyerCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Buyer> lastShownList = model.getFilteredBuyerList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
        }

        Buyer buyerToDisplay = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_LIST_BUYER_SUCCESS, Messages.format(buyerToDisplay)));
    }
}
