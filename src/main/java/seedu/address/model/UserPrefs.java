package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;


/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path buyersFilePath = Paths.get("data" , "buyers.json");
    private Path sellersFilePath = Paths.get("data", "buyers.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAddressBookFilePaths(newUserPrefs.getBuyersFilePath(), newUserPrefs.getSellersFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getBuyersFilePath() {
        return buyersFilePath;
    }

    public Path getSellersFilePath() {
        return sellersFilePath;
    }

    public void setAddressBookFilePaths(Path buyersFilePath, Path sellersFilePath) {
        requireAllNonNull(sellersFilePath, buyersFilePath);
        this.buyersFilePath = buyersFilePath;
        this.sellersFilePath = sellersFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserPrefs)) {
            return false;
        }

        UserPrefs otherUserPrefs = (UserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && buyersFilePath.equals(otherUserPrefs.buyersFilePath)
                && sellersFilePath.equals(otherUserPrefs.sellersFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, buyersFilePath, sellersFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location(buyers) : " + buyersFilePath);
        sb.append("\nLocal data file location(sellers) : " + sellersFilePath);
        return sb.toString();
    }

}
