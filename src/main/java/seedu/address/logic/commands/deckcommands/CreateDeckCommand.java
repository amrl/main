package seedu.address.logic.commands.deckcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.Deck;

/**
 * Creates a deck in the library.
 */
public class CreateDeckCommand extends Command {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a deck in the library.\n"
            + "Parameters: "
            + "NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + "Japanese 2";

    public static final String MESSAGE_SUCCESS = "New deck added: %1$s";
    public static final String MESSAGE_DUPLICATE_DECK = "This deck already exists in the library.";

    private final Deck toAdd;

    /**
     * Creates an CreateDeckCommand to add the specified {@code Deck}
     */
    public CreateDeckCommand(Deck deck) {
        requireNonNull(deck);
        toAdd = deck;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasDeck(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DECK);
        }

        model.createDeck(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateDeckCommand // instanceof handles nulls
                && toAdd.equals(((CreateDeckCommand) other).toAdd));
    }
}
