package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.Comparator;

import seedu.address.logic.CommandWarnings;
import seedu.address.logic.commands.SortBuyerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.displayable.buyer.Buyer;

/**
 * Parses input arguments and creates a new SortBuyerCommand object
 */
public class SortBuyerCommandParser implements Parser<SortBuyerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortBuyerCommand
     * and returns a SortBuyerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortBuyerCommand parse(String args, CommandWarnings commandWarnings) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ADDRESS,
                PREFIX_HOUSE_INFO, PREFIX_PRIORITY);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_HOUSE_INFO, PREFIX_PRIORITY);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            switch (argMultimap.getValue(PREFIX_NAME).get()) {

            case ("a"):
                Comparator<Buyer> ascendingNameComparator = (o1, o2) ->
                        o1.getName().fullName.toLowerCase().compareTo(o2.getName().fullName.toLowerCase());
                return new SortBuyerCommand(ascendingNameComparator);

            case ("d"):
                Comparator<Buyer> descendingNameComparator = (o1, o2) ->
                        o2.getName().fullName.toLowerCase().compareTo(o1.getName().fullName.toLowerCase());
                return new SortBuyerCommand(descendingNameComparator);

            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortBuyerCommand.MESSAGE_USAGE));
            }
        } else if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            switch (argMultimap.getValue(PREFIX_ADDRESS).get()) {

            case ("a"):
                Comparator<Buyer> ascendingAddressComparator = (o1, o2) ->
                        o1.getAddress().value.toLowerCase().compareTo(o2.getAddress().value.toLowerCase());
                return new SortBuyerCommand(ascendingAddressComparator);

            case ("d"):
                Comparator<Buyer> descendingAddressComparator = (o1, o2) ->
                        o2.getAddress().value.toLowerCase().compareTo(o1.getAddress().value.toLowerCase());
                return new SortBuyerCommand(descendingAddressComparator);

            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortBuyerCommand.MESSAGE_USAGE));
            }
        } else if (argMultimap.getValue(PREFIX_HOUSE_INFO).isPresent()) {
            switch (argMultimap.getValue(PREFIX_HOUSE_INFO).get()) {

            case ("a"):
                Comparator<Buyer> ascendingHouseInfoComparator = (o1, o2) ->
                        o1.getHouseInfo().info.toLowerCase().compareTo(o2.getHouseInfo().info.toLowerCase());
                return new SortBuyerCommand(ascendingHouseInfoComparator);

            case ("d"):
                Comparator<Buyer> descendingHouseInfoComparator = (o1, o2) ->
                        o2.getHouseInfo().info.toLowerCase().compareTo(o1.getHouseInfo().info.toLowerCase());
                return new SortBuyerCommand(descendingHouseInfoComparator);

            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortBuyerCommand.MESSAGE_USAGE));
            }
        } else if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            switch (argMultimap.getValue(PREFIX_PRIORITY).get()) {

            case ("a"):
                Comparator<Buyer> ascendingPriorityComparator = (o1, o2) ->
                        o2.getPriority().value.compareTo(o1.getPriority().value);
                return new SortBuyerCommand(ascendingPriorityComparator);

            case ("d"):
                Comparator<Buyer> descendingPriorityComparator = (o1, o2) ->
                        o1.getPriority().value.compareTo(o2.getPriority().value);
                return new SortBuyerCommand(descendingPriorityComparator);

            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortBuyerCommand.MESSAGE_USAGE));
            }
        } else {
            return new SortBuyerCommand(null);
        }
    }
}
