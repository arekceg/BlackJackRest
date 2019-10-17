package com.arek.blackjack.deck;

import com.arek.blackjack.deck.card.Card;
import com.arek.blackjack.exceptions.BlackJackException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "decks")
@NoArgsConstructor
@Slf4j
public class Deck {

	@ElementCollection
	@CollectionTable(name = "decks_cards",
			joinColumns = @JoinColumn(name = "deck_id"))
	private final Set<Card> cards = new LinkedHashSet<>();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;

	public void shuffle() {
		if (cards.size() == 0) {
			log.error("Deck is out of cards");
			throw new OutOfCardsException("Deck empty! Cannot shuffle an empty deck!");
		}
		log.info("Shuffling deck");
		List<Card> cardsList = new ArrayList<>(cards);
		Collections.shuffle(cardsList);
		cards.clear();
		if (cards.addAll(cardsList)) {
			log.info("Deck shuffled");
		} else {
			log.error("Error adding shuffled cards to deck");
		}
	}

	public void addCards(List<Card> cardsToAdd) {
		if (cardsToAdd == null) throw new BlackJackException("Cannot add null as cards to deck");
		if (cards.addAll(cardsToAdd)) {
			log.info("Added " + cardsToAdd.size() + " cards to deck");
		} else {
			log.error("Error adding cards to deck");
		}
	}

	public List<Card> getCardsAsList() {
		return Collections.unmodifiableList(new ArrayList<>(cards));
	}

	Card drawCardFromDeck() {
		Card drawnCard = cards.stream().findFirst().orElseThrow(() ->
				new OutOfCardsException("Deck is out of cards!"));
		cards.remove(drawnCard);
		log.info("Drawn card " + drawnCard.toString() + " from deck. Cards left in deck: " + cards.size());
		return drawnCard;
	}

	void logInfo(String msg){
		log.info("[DECK] + ");
	}
}