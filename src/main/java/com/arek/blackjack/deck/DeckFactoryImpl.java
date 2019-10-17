package com.arek.blackjack.deck;

import com.arek.blackjack.deck.card.Card;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeckFactoryImpl implements DeckFactory {

	@Override
	public Deck getNewDeck() {
		log.info("Creating new deck");
		Deck deck = new Deck();
		deck.addCards(Card.getAllCards());
		deck.shuffle();
		log.info("New deck created");
		return deck;
	}
}
