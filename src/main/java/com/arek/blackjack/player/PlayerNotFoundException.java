package com.arek.blackjack.player;

import com.arek.blackjack.exceptions.BlackJackException;

public class PlayerNotFoundException extends BlackJackException {
	public PlayerNotFoundException(String message) {
		super(message);
	}
}
