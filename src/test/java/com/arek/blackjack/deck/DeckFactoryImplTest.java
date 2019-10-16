package com.arek.blackjack.deck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class DeckFactoryImplTest {

	private DeckFactory deckFactory = new DeckFactoryImpl();

	@BeforeEach
	public void setUp(){
	}

	@Test
	void testShouldReturnNewDeckFullOfCards() {
		Deck newDeck = deckFactory.getNewDeck();
		assertNotNull(newDeck);
		assertEquals(52, newDeck.getCardsAsList().size());
	}
}