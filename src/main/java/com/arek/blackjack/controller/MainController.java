package com.arek.blackjack.controller;

import com.arek.blackjack.card.Card;
import com.arek.blackjack.card.CardService;
import com.arek.blackjack.deck.Deck;
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

	private CardService cardService;
	private DeckService deckService;

	@GetMapping("/allcards")
	public ResponseEntity<List<Card>> getAllCards() {
		List<Card> allCards = cardService.getAllCards();
		if (allCards.size() > 0) {
			return new ResponseEntity<>(allCards, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/deck")
	public ResponseEntity<Deck> getDeckOfCards() {
		Deck deck = new Deck(deckService, cardService);
		deckService.saveDeck(deck);
		return new ResponseEntity<>(deck, HttpStatus.OK);
	}

	@GetMapping("deck/cardfromdeck/{deckId}")
	public ResponseEntity<Card> getCardFromDeck(@PathVariable Long deckId){
		Deck deck = deckService.findDeckById(deckId);
		Card drawnCard = deck.getCardFromDeck();
		return new ResponseEntity<>(drawnCard,HttpStatus.OK);
	}


	@GetMapping("service/cardfromdeck/{deckId}")
	public ResponseEntity<Card> getCardFromDeckViaService(@PathVariable Long deckId){
		Card drawnCard = deckService.drawCardFromDeckByDeckId(deckId);
		return new ResponseEntity<>(drawnCard,HttpStatus.OK);
	}
}
