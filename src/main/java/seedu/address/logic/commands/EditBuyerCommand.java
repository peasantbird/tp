package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Edits a person in the address book.
 */
public class EditBuyerCommand extends Command{

    public static final String COMMAND_WORD = "edit-b";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a buyer's details. "
            + "Parameters: "
            + "INDEX "
            + "/FIELD "
            + "NEW_FIELD_VALUE "
            + "Example: " + COMMAND_WORD + " 1 /name Bob";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from edit buyer");
    }
}
