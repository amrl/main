package seedu.address.model.deck;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_FRENCH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCards.getTypicalJapCards;
import static seedu.address.testutil.TypicalDecks.JAPANESE_DECK;
import static seedu.address.testutil.TypicalDecks.MALAY_DECK;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DeckBuilder;

public class DeckTest {

    @Test
    public void isSameDeck() {
        // same object -> returns true
        assertTrue(JAPANESE_DECK.isSameDeck(JAPANESE_DECK));

        // null -> returns false
        assertFalse(JAPANESE_DECK.isSameDeck(null));

        // rename Japanese to French -> returns false
        Deck renamed = new DeckBuilder(JAPANESE_DECK).withName(VALID_NAME_FRENCH).build();
        assertFalse(JAPANESE_DECK.isSameDeck(JAPANESE_DECK));


        // new deck with same name, same cards -> returns true
        Deck newDeck = new DeckBuilder()
                .withName("Japanese")
                .withCards(getTypicalJapCards()).build();
        assertTrue(JAPANESE_DECK.isSameDeck(newDeck));

        // new deck with same name, no cards -> return true
        newDeck = new DeckBuilder()
                .withName("Japanese").build();
        assertTrue(JAPANESE_DECK.isSameDeck(newDeck));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Deck japaneseCopy = new DeckBuilder(JAPANESE_DECK).build();
        assertTrue(JAPANESE_DECK.equals(japaneseCopy));

        // same object -> returns true
        assertTrue(JAPANESE_DECK.equals(JAPANESE_DECK));

        // null -> returns false
        assertFalse(JAPANESE_DECK.equals(null));

        // different type -> returns false
        assertFalse(JAPANESE_DECK.equals(5));

        // different deck -> returns false
        assertFalse(JAPANESE_DECK.equals(MALAY_DECK));

        // different name -> returns false
        Deck renamed = new DeckBuilder(JAPANESE_DECK).withName(VALID_NAME_FRENCH).build();
        assertFalse(JAPANESE_DECK.equals(renamed));

    }
}
