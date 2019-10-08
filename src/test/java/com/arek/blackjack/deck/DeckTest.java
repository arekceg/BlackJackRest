package com.arek.blackjack.deck;


import com.arek.blackjack.card.Card;
import com.arek.blackjack.card.CardService;
import com.arek.blackjack.card.Rank;
import com.arek.blackjack.card.Suite;
import exceptions.OutOfCardsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Disabled
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DeckTest {

	private Deck deck;
	@Mock
	private CardService cardService;
	@Mock
	private DeckService deckService;
	private static List<Card> mockCardsList;

	@BeforeAll
	public static void init() {
		List<Suite> suites = Arrays.asList(Suite.values());
		List<Rank> ranks = Arrays.asList(Rank.values());
		mockCardsList = new ArrayList<>();
		ranks.forEach(rank -> {
			suites.forEach(suite -> {
				mockCardsList.add(Card.of(rank, suite));
			});
		});

	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void newDeckShouldContain52Cards() {
		//given
		when(cardService.getAllCards()).thenReturn(mockCardsList);
		deck = new Deck(deckService, cardService);
		//when
		//then
		ArrayList<Card> cardsAsList = deck.getCardsAsList();
		assertNotNull(cardsAsList);
		assertEquals(52, cardsAsList.size());
	}

	@Test
	public void shouldReturnRandomCard() {
		//given
		deck = new Deck(deckService, cardService);
		//when
		when(cardService.getAllCards()).thenReturn(mockCardsList);
		//then
		Card randomCard = deck.getCardFromDeck();
		assertNotNull(randomCard);
		assertTrue(randomCard.getValue() > 1 && randomCard.getValue() < 12);
	}

	@Test
	public void shouldThrowExceptionWhenDeckIsEmpty() {
		//given
		deck = new Deck(deckService, cardService);
		//when
		when(cardService.getAllCards()).thenReturn(mockCardsList);
		//then
		assertThrows(OutOfCardsException.class, () -> {
			int count = 0;
			while (count < 53) {
				deck.getCardFromDeck();
				count++;
			}
		});
	}

	@Test
	public void shouldShuffleDeck() {
		//given
		deck = new Deck(deckService, cardService);
		//when
		when(cardService.getAllCards()).thenReturn(mockCardsList);
		//then
		Card firstCard = deck.getCardFromDeck();
		Card secondCard = deck.getCardFromDeck();
		Card thirdCard = deck.getCardFromDeck();
		deck.shuffle();
		assertNotEquals(firstCard, deck.getCardFromDeck());
		assertNotEquals(secondCard, deck.getCardFromDeck());
		assertNotEquals(thirdCard, deck.getCardFromDeck());
	}


}