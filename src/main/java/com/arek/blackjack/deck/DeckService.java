package com.arek.blackjack.deck;

import com.arek.blackjack.card.Card;
import com.arek.blackjack.exceptions.BlackJackException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//TODO: Popatrzeć jak wygląda wywołanie metody @Transactional przez debugger - różnce między wywoływaniem public a private
//TODO: Elementy springowe utworzone ręcznie nie będą się zachowywać jak obiety springowe
@Service
@AllArgsConstructor
@Transactional
public class DeckService {
	private final DeckRepository deckRepository;

	//TODO: SaveDeck powinno być wykonywane transakcyjnie przy rozpoczęciu gry!
	public void saveDeck(Deck deck) {
		deckRepository.save(deck);
	}

	public Deck findDeckById(Long deckId) {
		return deckRepository.findById(deckId).orElseThrow(() ->
				new BlackJackException("No deck found by id: " + deckId.toString()));
	}

	//TODO: Not very DDD, functionality should be in Deck class
	public Card drawCardFromDeck(Deck deck) {
		return deck.drawCardFromDeck();
	}

}
