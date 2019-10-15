package com.arek.blackjack.deck;

import com.arek.blackjack.card.Card;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeckFactoryImpl implements DeckFactory {

	private DeckService deckService;

	@Override
	public Deck getNewDeck() {
		Deck deck = new Deck();
		deck.cards.addAll(Card.getAllCards());
		deck.shuffle();
		deckService.saveDeck(deck);
		return deck;
	}
}
