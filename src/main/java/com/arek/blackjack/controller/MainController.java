package com.arek.blackjack.controller;

import com.arek.blackjack.card.Card;
import com.arek.blackjack.deck.Deck;
import com.arek.blackjack.deck.DeckFactory;
import com.arek.blackjack.deck.DeckService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {

	private DeckService deckService;
	private DeckFactory deckFactory;

	@GetMapping("/deck")
	public ResponseEntity<Deck> getDeckOfCards() {
		Deck deck = deckFactory.getNewDeck();
		return new ResponseEntity<>(deck, HttpStatus.OK);
	}

	@GetMapping("deck/{deckId}/draw")
	public ResponseEntity<Card> getCardFromDeck(@PathVariable Long deckId){
		Deck deck = deckService.findDeckById(deckId);
		Card drawnCard = deckService.drawCardFromDeck(deck);
		return new ResponseEntity<>(drawnCard,HttpStatus.OK);
	}
}
