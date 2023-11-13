package seedu.address.model.displayable.seller;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.Comparator;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.displayable.SortOrder;

public class SellerComparator implements Comparator<Seller> {

    private static final SellerComparator ASCENDING_NAME_COMPARATOR = new SellerComparator((o1, o2) ->
            o1.getName().fullName.toLowerCase().compareTo(o2.getName().fullName.toLowerCase()));
    private static final SellerComparator DESCENDING_NAME_COMPARATOR = new SellerComparator((o1, o2) ->
            o2.getName().fullName.toLowerCase().compareTo(o1.getName().fullName.toLowerCase()));
    private static final SellerComparator ASCENDING_ADDRESS_COMPARATOR = new SellerComparator((o1, o2) ->
            o1.getAddress().value.toLowerCase().compareTo(o2.getAddress().value.toLowerCase()));
    private static final SellerComparator DESCENDING_ADDRESS_COMPARATOR = new SellerComparator((o1, o2) ->
            o2.getAddress().value.toLowerCase().compareTo(o1.getAddress().value.toLowerCase()));
    private static final SellerComparator ASCENDING_HOUSE_INFO_COMPARATOR = new SellerComparator((o1, o2) ->
            o1.getHouseInfo().info.toLowerCase().compareTo(o2.getHouseInfo().info.toLowerCase()));
    private static final SellerComparator DESCENDING_HOUSE_INFO_COMPARATOR = new SellerComparator((o1, o2) ->
            o2.getHouseInfo().info.toLowerCase().compareTo(o1.getHouseInfo().info.toLowerCase()));
    private static final SellerComparator ASCENDING_PRIORITY_COMPARATOR = new SellerComparator((o1, o2) ->
            o2.getPriority().value.compareTo(o1.getPriority().value));
    private static final SellerComparator DESCENDING_PRIORITY_COMPARATOR = new SellerComparator((o1, o2) ->
            o1.getPriority().value.compareTo(o2.getPriority().value));
    private static final SellerComparator DEFAULT_COMPARATOR = null;

    public static final String MESSAGE_INVALID_SORT_ORDER =
            "Seller comparator only works with ascending and descending sort orders!";
    public static final String MESSAGE_UNSUPPORTED_PREFIX = "Seller comparator does not support that prefix!";
    private final Comparator<Seller> sellerComparator;

    private SellerComparator(Comparator<Seller> comparator) {
        this.sellerComparator = comparator;
    }

    public static SellerComparator of(Prefix prefix, SortOrder sortOrder) {
        requireNonNull(prefix);
        requireNonNull(sortOrder);

        if (prefix.equals(PREFIX_NAME)) {
            switch (sortOrder.orderType) {
            case ASCENDING:
                return ASCENDING_NAME_COMPARATOR;
            case DESCENDING:
                return DESCENDING_NAME_COMPARATOR;
            default:
                throw new IllegalArgumentException(MESSAGE_INVALID_SORT_ORDER);
            }
        } else if (prefix.equals(PREFIX_ADDRESS)) {
            switch (sortOrder.orderType) {
            case ASCENDING:
                return ASCENDING_ADDRESS_COMPARATOR;
            case DESCENDING:
                return DESCENDING_ADDRESS_COMPARATOR;
            default:
                throw new IllegalArgumentException(MESSAGE_INVALID_SORT_ORDER);
            }
        } else if (prefix.equals(PREFIX_HOUSE_INFO)) {
            switch (sortOrder.orderType) {
            case ASCENDING:
                return ASCENDING_HOUSE_INFO_COMPARATOR;
            case DESCENDING:
                return DESCENDING_HOUSE_INFO_COMPARATOR;
            default:
                throw new IllegalArgumentException(MESSAGE_INVALID_SORT_ORDER);
            }
        } else if (prefix.equals(PREFIX_PRIORITY)) {
            switch (sortOrder.orderType) {
            case ASCENDING:
                return ASCENDING_PRIORITY_COMPARATOR;
            case DESCENDING:
                return DESCENDING_PRIORITY_COMPARATOR;
            default:
                throw new IllegalArgumentException(MESSAGE_INVALID_SORT_ORDER);
            }
        } else {
            throw new IllegalArgumentException(MESSAGE_UNSUPPORTED_PREFIX);
        }
    }

    public static SellerComparator of() {
        return DEFAULT_COMPARATOR;
    }

    @Override
    public int compare(Seller o1, Seller o2) {
        return sellerComparator.compare(o1, o2);
    }
}
