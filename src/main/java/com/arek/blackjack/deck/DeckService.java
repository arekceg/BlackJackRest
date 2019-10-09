package com.arek.blackjack.deck;

import com.arek.blackjack.card.Card;
import com.arek.blackjack.card.CardService;
import exceptions.BlackJackException;
import exceptions.OutOfCardsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeckService {
	private final DeckRepository deckRepository;
	private final CardService cardService;

	//TODO: Not very DDD, functionality should be in Deck class
	public Deck getNewDeck() {
		Deck newDeck = new Deck(this, cardService);
		fillDeckWithCards(newDeck, cardService.getAllCards()).shuffle();
		return newDeck;
	}

	//TODO: Not very DDD, functionality should be in Deck class
	private Deck fillDeckWithCards(Deck newDeck, List<Card> cards) {
		if (cards == null || cards.size() < 1) {
			throw new OutOfCardsException("No cards to fill deck!");
		}
		newDeck.fillDeckWithCards(cards);
		return newDeck;
	}

	public void saveDeck(Deck deck) {
		deckRepository.save(deck);
	}

	public Deck findDeckById(Long deckId) {
		return deckRepository.findById(deckId).orElseThrow(() ->
				new BlackJackException("No deck found by id: " + deckId.toString()));
	}

	//TODO: Not very DDD, functionality should be in Deck class
	public Card drawCardFromDeckByDeckId(Long deckId) {
		Long firstCardId = getFirstCardIdFromDeckByDeckId(deckId).orElseThrow(() ->
				new OutOfCardsException("Deck is out of cards!"));
		deckRepository.removeCardFromDeckByDeckId(deckId, firstCardId);
		return cardService.getCardById(firstCardId);
	}

	private Optional<Long> getFirstCardIdFromDeckByDeckId(Long deckId) {
		Optional<Long> cardIdOptional =
				Optional.ofNullable(deckRepository.getFirstCardIdFromDeckByDeckId(deckId));
		return cardIdOptional;
	}
}
