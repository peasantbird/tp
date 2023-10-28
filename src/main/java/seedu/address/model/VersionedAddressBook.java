package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

public class VersionedAddressBook extends AddressBook {
    private List<AddressBook> addressBookStateList;
    private int currentStatePointer;

    public VersionedAddressBook(AddressBook ab) {
        addressBookStateList = new ArrayList<>();
        addressBookStateList.add(ab);
        currentStatePointer = 0;
    }

    public void commit(AddressBook ab) {
        addressBookStateList.add(ab);
        currentStatePointer++;
    }

    public void undo() {
        currentStatePointer--;
    }

    public void redo() {
        currentStatePointer++;
    }

    public void canUndo() {
        if (currentStatePointer == 0) {

        }
    }

    public void canRedo() {
        if (currentStatePointer == addressBookStateList.size() - 1) {

        }
    }
}
