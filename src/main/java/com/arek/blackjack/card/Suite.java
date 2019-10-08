package com.arek.blackjack.card;

import javax.persistence.*;


public enum Suite {
	SPADES("SPADES"),
	HEARTS("HEARTS"),
	CLUBS("CLUBS"),
	DIAMONDS("DIAMONDS");


	private Long id;

	private final String suite;

	Suite(String suite) {
		this.suite = suite;
	}

	public String suite(){
		return suite;
	}
}
