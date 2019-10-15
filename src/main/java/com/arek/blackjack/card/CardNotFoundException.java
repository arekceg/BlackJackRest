package com.arek.blackjack.card;

import com.arek.blackjack.exceptions.BlackJackException;

public class CardNotFoundException extends BlackJackException {
	public CardNotFoundException(String message) {
		super(message);
	}
}

