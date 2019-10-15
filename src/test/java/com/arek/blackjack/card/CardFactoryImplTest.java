package com.arek.blackjack.card;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardFactoryImplTest {
	private static CardFactory cardFactory;

	@BeforeAll
	public static void setUp(){
		cardFactory = new CardFactoryImpl();
	}

	@Test
	public void shouldCreateCorrectCard(){
		Card twoOfSpades = cardFactory.getCard(Rank.TWO, Suite.SPADES);
		Card aceOfDiamonds = cardFactory.getCard(Rank.ACE, Suite.DIAMONDS);

		assertEquals(Card.TWO_SPADES, twoOfSpades);
		assertEquals(Card.ACE_DIAMONDS, aceOfDiamonds);
	}
}