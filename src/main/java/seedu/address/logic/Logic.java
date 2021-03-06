package seedu.address.logic;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deck.Deck;
import seedu.address.model.util.Mode;

import java.nio.file.Path;

/**
 * API of the Logic component.
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /** Returns an unmodifiable view of the filtered list of decks. */
    ObservableList<Deck> getFilteredDeckList();

    /**
     * Returns the user prefs' library file path.
     */
    Path getLibraryFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Get the selected deck
     */
    Deck getCurrentDeck();

    ReadOnlyProperty<Deck> selectedDeckProperty();

    ReadOnlyProperty<Mode> currentModeProperty();

    /**
     * Returns the mode of the model manager.
     */
    Mode getMode();

    void setSelectedDeck(Deck deck);
}
