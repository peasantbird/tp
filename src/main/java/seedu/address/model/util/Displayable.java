package seedu.address.model.util;

import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * Represents a displayable that has an ability to display as a UIPart and can do a lesser equality check with a method
 * named isSameDisplayable.
 */
public interface Displayable {
    UiPart<Region> display(int index);
    boolean isSameDisplayable(Displayable displayable);
}
