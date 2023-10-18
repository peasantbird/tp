package seedu.address.model.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.util.Displayable;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of displayables that enforces uniqueness between its elements and does not allow nulls.
 * A displayable is considered unique by comparing using {@code Displayable#isSameDisplayable(Displayable)}.
 * As such, adding and updating of displayables uses Displayable#isSameDisplayable(Displayable) for equality to
 * ensure that the displayable being added or updated is unique in terms of identity in the UniqueDisplayableList.
 * However, the removal of a displayable uses Displayable#equals(Object)
 * to ensure that the displayable with exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Displayable#isSameDisplayable(Displayable)
 */
public class UniqueDisplayableList<T extends Displayable> implements Iterable<T> {

    private final ObservableList<Displayable> internalList = FXCollections.observableArrayList();
    private final ObservableList<Displayable> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent displayable as the given argument.
     */
    public boolean contains(Displayable toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDisplayable);
    }

    /**
     * Adds a displayable to the list.
     * The displayable must not already exist in the list.
     */
    public void add(Displayable toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            //TODO change this when we add a more general exception.
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the displayable {@code target} in the list with {@code editedDisplayable}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedDisplayable} must not be the same as another existing person in the list.
     */
    public void setDisplayable(Displayable target, Displayable editedDisplayable) {
        requireAllNonNull(target, editedDisplayable);

        int index = internalList.indexOf(target);
        if (index == -1) {
            //TODO replace this exception
            throw new PersonNotFoundException();
        }

        if (!target.isSameDisplayable(editedDisplayable) && contains(editedDisplayable)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedDisplayable);
    }

    /**
     * Removes the equivalent displayable from the list.
     * The displayable must exist in the list.
     */
    public void remove(Displayable toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            //TODO replace exception
            throw new PersonNotFoundException();
        }
    }

    // TODO: Ascertain whether this is necessary, if not, remove this method and its JUnit tests
    public void setDisplayables(UniqueDisplayableList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code displayables}.
     * {@code displayables} must not contain duplicate displayables.
     */
    public void setDisplayables(List<Displayable> displayables) {
        requireAllNonNull(displayables);
        if (!displayablesAreUnique(displayables)) {
            //TODO change exception
            throw new DuplicatePersonException();
        }

        internalList.setAll(displayables);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Displayable> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Displayable> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueDisplayableList)) {
            return false;
        }

        UniqueDisplayableList otherUniqueDisplayableList = (UniqueDisplayableList) other;
        return internalList.equals(otherUniqueDisplayableList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean displayablesAreUnique(List<Displayable> displayables) {
        for (int i = 0; i < displayables.size() - 1; i++) {
            for (int j = i + 1; j < displayables.size(); j++) {
                if (displayables.get(i).isSameDisplayable(displayables.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}