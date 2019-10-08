package com.arek.blackjack.card;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CardTest {

	@BeforeAll
	public static void setUp() throws Exception {
	}

	@Test
	public void shouldReturnProperCardValueBasedOnRank() {
		Card tenOfSpades = Card.of(Rank.TEN, Suite.SPADES);
		Card aceOfHears = Card.of(Rank.ACE, Suite.HEARTS);

		assertEquals(10, tenOfSpades.getValue());
		assertEquals(11, aceOfHears.getValue());
	}

	@Test
	public void shouldCheckThatCardsAreEqual() {
		Card firstCard = Card.of(Rank.FIVE, Suite.SPADES);
		Card secondCard = Card.of(Rank.FIVE, Suite.SPADES);

		assertEquals(firstCard, secondCard);
	}

	@Test
	public void shouldCheckThatCardsAreNotEqual() {
		Card firstCard = Card.of(Rank.FIVE, Suite.SPADES);
		Card secondCard = Card.of(Rank.TWO, Suite.SPADES);

		assertNotEquals(firstCard, secondCard);
	}

	@Test
	public void shoudlCheckIfCardHasHigherValue() {
		Card firstCard = Card.of(Rank.FIVE, Suite.SPADES);
		Card secondCard = Card.of(Rank.TWO, Suite.SPADES);

		assertEquals(firstCard.compareTo(secondCard), 1);
	}
}