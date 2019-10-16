package com.arek.blackjack.deck.card;

import org.springframework.stereotype.Component;

@Component
class CardFactoryImpl implements CardFactory {

	@Override
	public Card getCard(Rank rank, Suite suite) {
		return Card.valueOf(rank.name().toUpperCase() + "_" + suite.name().toUpperCase());
	}
}
