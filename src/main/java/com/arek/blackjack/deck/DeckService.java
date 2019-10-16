package com.arek.blackjack.deck;

import com.arek.blackjack.deck.card.Card;
import com.arek.blackjack.exceptions.BlackJackException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//TODO: Popatrzeć jak wygląda wywołanie metody @Transactional przez debugger - różnce między wywoływaniem public a private
//TODO: Elementy springowe utworzone ręcznie nie będą się zachowywać jak obiety springowe
@Service
@AllArgsConstructor
@Transactional
public class DeckService {
	private final DeckRepository deckRepository;
	private final DeckFactory deckFactory;

	//TODO: SaveDeck powinno być wykonywane transakcyjnie przy rozpoczęciu gry!
	public Deck getNewDeckAndPersist() {
		Deck deck = deckFactory.getNewDeck();
		return deckRepository.save(deck);
	}

	public Deck getDeckById(Long deckId) {
		return deckRepository.findById(deckId).orElseThrow(() ->
				new BlackJackException("No deck found by id: " + deckId.toString()));
	}

	public Card drawCardFromDeck(Deck deck) {
		return deck.drawCardFromDeck();
	}

}
