package com.arek.blackjack.deck;

import com.arek.blackjack.exceptions.BlackJackException;

public class OutOfCardsException extends BlackJackException {
	public OutOfCardsException(String message) {
		super(message);
	}
}
