package com.arek.blackjack.deck;

import com.arek.blackjack.deck.card.Card;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeckFactoryImpl implements DeckFactory {

	@Override
	public Deck getNewDeck() {
		Deck deck = new Deck();
		deck.addCards(Card.getAllCards());
		deck.shuffle();
		return deck;
	}
}
