package seedu.address.model.util;

import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

public interface Displayable {
    UiPart<Region> display(int index);

    boolean isSameDisplayable(Displayable displayable);
}
