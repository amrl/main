package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;
import seedu.address.model.deck.card.FrontFace;

/**
 * Jackson-friendly version of {@link Deck}.
 */
class JsonAdaptedDeck {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Deck's %s field is missing!";

    private final String name;
    private List<JsonAdaptedCard> cards = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedDeck} with the given deck details.
     */
    @JsonCreator
    public JsonAdaptedDeck(@JsonProperty("name") String name, @JsonProperty("cards") List<JsonAdaptedCard> cards) {
        this.name = name;
        this.cards.addAll(cards);
    }

    /**
     * Converts a given {@code Deck} into this class for Jackson use.
     */
    public JsonAdaptedDeck(Deck source) {
        name = source.getName().name;
        this.cards = source.asUnmodifiableObservableList()
                .stream()
                .map(card -> new JsonAdaptedCard(card))
                .collect(Collectors.toList());
    }

    /**
     * Converts this Jackson-friendly adapted deck object into the model's {@code Deck} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted deck.
     */
    public Deck toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }

        Deck modelDeck = new Deck(new Name(name));
        for (JsonAdaptedCard card : cards) {
            FrontFace newFrontFace = new FrontFace(card.getFrontFace());
            BackFace newBackFace = new BackFace(card.getBackFace());
            modelDeck.add(new Card(newFrontFace, newBackFace));
        }

        return modelDeck;
    }
}
