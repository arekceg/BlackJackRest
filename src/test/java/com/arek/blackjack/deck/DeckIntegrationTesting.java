package com.arek.blackjack.deck;

import com.arek.blackjack.deck.card.Card;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
public class DeckIntegrationTesting {

	@Autowired
	private TestEntityManager deckEntityManager;
	@Autowired
	private DeckRepository deckRepository;
	@Mock
	private DeckFactory deckFactory;
	private Deck deck;

	@BeforeAll
	public static void setUp(){
	}

	@BeforeEach
	public void init(){
		deck = new Deck();
		deck.addCards(Card.getAllCards());
		when(deckFactory.getNewDeck()).thenReturn(deck);
	}

	@Test
	public void testShouldSaveDeckFullOfCardsToDatabase(){
		Deck savedDeck = deckEntityManager.persist(deckFactory.getNewDeck());
		assertNotNull(savedDeck);
		assertNotNull(savedDeck.getCardsAsList());
		assertEquals(52, savedDeck.getCardsAsList().size());
	}

	@Test
	public void testShouldDrawCardFromDeck(){
		Long deckId = (Long) deckEntityManager.persistAndGetId(deckFactory.getNewDeck());
		Deck deckFromDb = deckEntityManager.find(Deck.class, deckId);
		deckFromDb.drawCardFromDeck();
	}
}
