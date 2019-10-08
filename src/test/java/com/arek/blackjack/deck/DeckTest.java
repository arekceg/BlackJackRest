package com.arek.blackjack.deck;


import com.arek.blackjack.card.Card;
import exceptions.OutOfCardsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DeckTest {

	private static Deck deck;

	@BeforeEach
	public void setUp() {
		deck = Deck.getNew();
	}

	@Test
	public void newDeckShouldContain52Cards() {
		//given
		ArrayList<Card> cards = deck.getCards();
		//when
		//then
		assertNotNull(cards);
		assertEquals(52, cards.size());
	}

	@Test
	public void shouldReturnRandomCard() {
		Card randomCard = deck.getCard();
		assertNotNull(randomCard);
		assertTrue(randomCard.getValue() > 1 && randomCard.getValue() < 12);
	}

	@Test
	public void shouldThrowExceptionWhenDeckIsEmpty() {
		assertThrows(OutOfCardsException.class, () -> {
			int count = 0;
			while (count < 53) {
				deck.getCard();
				count++;
			}
		});
	}

	@Test
	public void shouldShuffleDeck() {
		Card firstCard = deck.getCard();
		Card secondCard = deck.getCard();
		Card thirdCard = deck.getCard();
		deck.shuffle();
		assertNotEquals(firstCard, deck.getCard());
		assertNotEquals(secondCard, deck.getCard());
		assertNotEquals(thirdCard, deck.getCard());
	}


}