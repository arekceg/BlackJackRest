package com.arek.blackjack.deck;

import com.arek.blackjack.deck.card.Card;
import com.arek.blackjack.exceptions.BlackJackException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "decks")
@NoArgsConstructor
public class Deck {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;

	@ElementCollection
	@CollectionTable(name = "decks_cards",
			joinColumns = @JoinColumn(name = "deck_id"))
	private final Set<Card> cards = new LinkedHashSet<>();

	public void shuffle() {
		if (cards.size() == 0) {
			throw new OutOfCardsException("Deck empty! Cannot shuffle an empty deck!");
		}
		List<Card> cardsList = new ArrayList<>(cards);
		Collections.shuffle(cardsList);
		cards.clear();
		cards.addAll(cardsList);
	}

	public void addCards(List<Card> cardsToAdd) {
		if(cardsToAdd == null) throw new BlackJackException("Cannot add null as cards to deck");
		cards.addAll(cardsToAdd);
	}

	public List<Card> getCardsAsList() {
		return Collections.unmodifiableList(new ArrayList<>(cards));
	}

	Card drawCardFromDeck() {
		Card drawnCard = cards.stream().findFirst().orElseThrow(() ->
				new OutOfCardsException("Deck is out of cards!"));
		cards.remove(drawnCard);
		return drawnCard;
	}
}