package com.arek.blackjack.deck;

import com.arek.blackjack.card.Card;
import com.arek.blackjack.card.CardService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class DeckServiceIntegrationTest {

	@Autowired
	private CardService cardService;

	@Autowired
	private DeckService deckService;


	@BeforeEach
	public void setUp() {
		cardService.initCardsInDatabase();
	}

	@Test
	void getNewDeck() {
		Deck deck = deckService.getNewDeck();
		assertNotNull(deck);
		assertEquals(52, deck.getCardsAsList().size());
	}

	@Test
	void drawCardFromDeckByDeckId() {
		Deck deck = deckService.getNewDeck();
		Card card = deckService.drawCardFromDeckByDeckId(deck.getId());
		assertNotNull(card);
		assertTrue(card.getValue() > 0 && card.getValue() < 12);
	}
}