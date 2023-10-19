package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.util.Displayable;

/**
 * Panel containing the list of persons.
 */
public class DisplayableListPanel extends UiPart<Region> {
    private static final String FXML = "DisplayableListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DisplayableListPanel.class);

    @FXML
    private ListView<Displayable> displayableListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public DisplayableListPanel(ObservableList<? extends Displayable> displayableList) {
        super(FXML);
        displayableListView.setItems((ObservableList<Displayable>) displayableList);
        displayableListView.setCellFactory(listView -> new DisplayableListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class DisplayableListViewCell extends ListCell<Displayable> {
        @Override
        protected void updateItem(Displayable displayable, boolean empty) {
            super.updateItem(displayable, empty);

            if (empty || displayable == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(displayable.display(getIndex() + 1).getRoot());
            }
        }
    }

}
