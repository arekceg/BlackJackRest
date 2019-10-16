package com.arek.blackjack.deck;

import com.arek.blackjack.deck.card.Card;
import com.arek.blackjack.exceptions.BlackJackException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class DeckTest {

	private static DeckFactory deckFactory;
	private Deck deck;

	@BeforeAll
	public static void init(){
		deckFactory = new DeckFactoryImpl();
	}

	@BeforeEach
	public void setUp() {
		deck = deckFactory.getNewDeck();
	}

	@Test
	void testAddCardsShouldThrowBlackJackException() {
		assertThrows(BlackJackException.class, () -> deck.addCards(null));
	}

	@Test
	void testAddCardsShouldAddCardsToDeck() {
		//given
		List<Card> cards = new ArrayList<>(Card.getAllCards());
		//when
		deck.addCards(cards);
		List<Card> cardsFromDeck = deck.getCardsAsList();
		//then
		assertNotNull(cardsFromDeck);
		assertEquals(52, cardsFromDeck.size());
	}

	@Test
	void testShuffleDeckShouldThrowException() {
		//when
		for (int i = 0; i < 52; i++) {
			deck.drawCardFromDeck();
		}
		//then
		assertThrows(BlackJackException.class, () -> deck.shuffle());
	}

	@Test
	void testShuffleShouldShuffleDeck() {
		//given
		deck.addCards(Card.getAllCards());
		List<Card> cardsFromDeck = deck.getCardsAsList();
		Card firstCard = cardsFromDeck.get(0);
		Card secondCard = cardsFromDeck.get(1);
		Card thirdCard = cardsFromDeck.get(2);
		//when
		deck.shuffle();
		cardsFromDeck = deck.getCardsAsList();
		//then
		assertNotEquals(firstCard.name(), cardsFromDeck.get(0).name());
		assertNotEquals(secondCard.name(), cardsFromDeck.get(1).name());
		assertNotEquals(thirdCard.name(), cardsFromDeck.get(2).name());
	}

	@Test
	void testDrawCardFromDeckShouldReturnCardAndDecreaseAmountOfCardsInDeck() {
		//given
		List<Card> cardsBeforeDraw = deck.getCardsAsList();
		//when
		Card drawnCard = deck.drawCardFromDeck();
		List<Card> cardsAfterDraw = deck.getCardsAsList();
		//then
		assertNotNull(drawnCard);
		assertNotEquals(cardsAfterDraw.size(), cardsBeforeDraw.size());
		assertEquals(1,cardsBeforeDraw.size() - cardsAfterDraw.size());
	}

	@Test
	void testDrawCardFromDeckShouldThrowException() {
		assertThrows(BlackJackException.class, () -> {
			for (int i = 0; i < 55; i++) {
				deck.drawCardFromDeck();
			}
		});
	}
}