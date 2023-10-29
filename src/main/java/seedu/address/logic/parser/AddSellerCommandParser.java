package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELLING_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.CommandWarnings;
import seedu.address.logic.commands.AddSellerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Email;
import seedu.address.model.displayable.HouseInfo;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Phone;
import seedu.address.model.displayable.seller.Seller;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddSellerCommand object
 */
public class AddSellerCommandParser implements Parser<AddSellerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddSellerCommand
     * and returns an AddSellerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddSellerCommand parse(String args, CommandWarnings commandWarnings) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_SELLING_ADDRESS, PREFIX_HOUSE_INFO, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_HOUSE_INFO,
                PREFIX_SELLING_ADDRESS, PREFIX_EMAIL) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSellerCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_SELLING_ADDRESS);
        Name name = ParserUtil.parseName(commandWarnings, argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(commandWarnings, argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(commandWarnings, argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(commandWarnings, argMultimap.getValue(PREFIX_ADDRESS).get());
        Address sellingAddress =
                ParserUtil.parseAddress(commandWarnings, argMultimap.getValue(PREFIX_SELLING_ADDRESS).get());
        HouseInfo houseInfo = ParserUtil.parseHouseInfo(commandWarnings, argMultimap.getValue(PREFIX_HOUSE_INFO).get());
        Set<Tag> tagList = ParserUtil.parseTags(commandWarnings, argMultimap.getAllValues(PREFIX_TAG));

        Seller seller = new Seller(name, phone, email, address, sellingAddress, houseInfo, tagList);

        return new AddSellerCommand(seller, commandWarnings);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
