package com.arek.blackjack.deck.card;

import com.arek.blackjack.exceptions.BlackJackException;

public class CardNotFoundException extends BlackJackException {
	public CardNotFoundException(String message) {
		super(message);
	}
}

