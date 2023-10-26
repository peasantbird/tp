package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.displayable.Priority;
import seedu.address.model.displayable.buyer.Buyer;

import java.util.List;

/**
 * Sets a buyer's priority based on displayed index in the address book.
 */
public class SetBuyerPriorityCommand extends Command {

    public static final String COMMAND_WORD = "priority-b";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets a priority level for the buyer, identified by index in the displayed buyer list. "
            + "Index must be a positive integer, while prio_lvl can be either 'high', 'medium', or 'low'.\n"
            + "Parameters: "
            + "INDEX "
            + "PRIO_LVL\n"
            + "Example: " + COMMAND_WORD + " 1" + " high";

    public static final String MESSAGE_SUCCESS = "The buyer's priority level has been set:\n%1$s";
    private final Index targetIndex;
    private final Priority priority;

    public SetBuyerPriorityCommand(Index targetIndex, Priority priority) {
        this.targetIndex = targetIndex;
        this.priority = priority;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Buyer> lastShownList = model.getFilteredBuyerList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
        }

        Buyer targetBuyer = lastShownList.get(targetIndex.getZeroBased());
        Buyer buyerWithPriority = getBuyerWithPriority(targetBuyer, this.priority);

        model.setBuyer(targetBuyer, buyerWithPriority);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(targetBuyer)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Buyer getBuyerWithPriority(Buyer targetBuyer, Priority priority) {
        assert targetBuyer != null;

        return new Buyer(
                targetBuyer.getName(),
                targetBuyer.getPhone(),
                targetBuyer.getEmail(),
                targetBuyer.getAddress(),
                targetBuyer.getBuyHouseInfo(),
                targetBuyer.getTags(),
                priority);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetBuyerPriorityCommand)) {
            return false;
        }

        SetBuyerPriorityCommand otherPriorityCommand = (SetBuyerPriorityCommand) other;
        return targetIndex.equals(otherPriorityCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("priority", priority)
                .toString();
    }
}
