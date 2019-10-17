package com.arek.blackjack.deck;

import com.arek.blackjack.deck.card.Card;
import com.arek.blackjack.exceptions.BlackJackException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//TODO: Popatrzeć jak wygląda wywołanie metody @Transactional przez debugger - różnce między wywoływaniem public a private
//TODO: Elementy springowe utworzone ręcznie nie będą się zachowywać jak obiety springowe
@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class DeckService {
	private final DeckRepository deckRepository;
	private final DeckFactory deckFactory;

	public Deck persistDeck(Deck deck){
		log.info("Persisting deck via DeckService");
		return deckRepository.save(deck);
	}
	//TODO: SaveDeck powinno być wykonywane transakcyjnie przy rozpoczęciu gry!
	public Deck getNewDeckAndPersist() {
		log.info("Creating and persisting a new deck");
		Deck deck = deckFactory.getNewDeck();
		log.info("Persisting deck");
		Deck savedDeck = deckRepository.save(deck);
		log.info("Persisted deck. ID:" + deck.getId());
		return savedDeck;
	}

	public Deck getDeckById(Long deckId) {
		return deckRepository.findById(deckId).orElseThrow(() ->
				new BlackJackException("No deck found by id: " + deckId.toString()));
	}

	public Card drawCardFromDeck(Deck deck) {
		return deck.drawCardFromDeck();
	}

}
