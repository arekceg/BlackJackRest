package com.arek.blackjack.deck.card;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class CardFactoryImpl implements CardFactory {

	@Override
	public Card getCard(Rank rank, Suite suite) {
		log.info("Creating a card: "+ rank +" of " + suite);
		return Card.valueOf(rank.name().toUpperCase() + "_" + suite.name().toUpperCase());
	}
}
