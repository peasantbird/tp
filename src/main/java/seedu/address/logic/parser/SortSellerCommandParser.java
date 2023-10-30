package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.commands.SortSellerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.displayable.seller.Seller;

import java.util.Comparator;

public class SortSellerCommandParser implements Parser<SortSellerCommand> {

    public SortSellerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ADDRESS,
                PREFIX_HOUSE_INFO, PREFIX_PRIORITY);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_HOUSE_INFO, PREFIX_PRIORITY);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            switch (argMultimap.getValue(PREFIX_NAME).get()) {

            case ("a"):
                Comparator<Seller> ascendingNameComparator =
                        (o1, o2) -> o1.getName().fullName.compareTo(o2.getName().fullName);
                return new SortSellerCommand(ascendingNameComparator);

            case ("d"):
                Comparator<Seller> descendingNameComparator =
                        (o1, o2) -> o2.getName().fullName.compareTo(o1.getName().fullName);
                return new SortSellerCommand(descendingNameComparator);

            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortSellerCommand.MESSAGE_USAGE));
            }
        } else if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            switch (argMultimap.getValue(PREFIX_ADDRESS).get()) {

            case ("a"):
                Comparator<Seller> ascendingAddressComparator =
                        (o1, o2) -> o1.getAddress().value.compareTo(o2.getAddress().value);
                return new SortSellerCommand(ascendingAddressComparator);

            case ("d"):
                Comparator<Seller> descendingAddressComparator =
                        (o1, o2) -> o2.getAddress().value.compareTo(o1.getAddress().value);
                return new SortSellerCommand(descendingAddressComparator);

            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortSellerCommand.MESSAGE_USAGE));
            }
        } else if (argMultimap.getValue(PREFIX_HOUSE_INFO).isPresent()) {
            switch (argMultimap.getValue(PREFIX_HOUSE_INFO).get()) {

            case ("a"):
                Comparator<Seller> ascendingHouseInfoComparator =
                        (o1, o2) -> o1.getHouseInfo().info.compareTo(o2.getHouseInfo().info);
                return new SortSellerCommand(ascendingHouseInfoComparator);

            case ("d"):
                Comparator<Seller> descendingHouseInfoComparator =
                        (o1, o2) -> o2.getHouseInfo().info.compareTo(o1.getHouseInfo().info);
                return new SortSellerCommand(descendingHouseInfoComparator);

            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortSellerCommand.MESSAGE_USAGE));
            }
        } else if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            switch (argMultimap.getValue(PREFIX_PRIORITY).get()) {

            case ("a"):
                Comparator<Seller> ascendingPriorityComparator =
                        (o1, o2) -> o1.getPriority().value.compareTo(o2.getPriority().value);
                return new SortSellerCommand(ascendingPriorityComparator);

            case ("d"):
                Comparator<Seller> descendingPriorityComparator =
                        (o1, o2) -> o2.getPriority().value.compareTo(o1.getPriority().value);
                return new SortSellerCommand(descendingPriorityComparator);

            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortSellerCommand.MESSAGE_USAGE));
            }
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortSellerCommand.MESSAGE_USAGE));
        }
    }
}
