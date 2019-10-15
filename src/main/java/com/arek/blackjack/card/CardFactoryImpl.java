package com.arek.blackjack.card;

import org.springframework.stereotype.Component;

@Component
public class CardFactoryImpl implements CardFactory {

	@Override
	public Card getCard(Rank rank, Suite suite) {
		return Card.valueOf(rank.name().toUpperCase() + "_" + suite.name().toUpperCase());
	}
}
