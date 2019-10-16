package com.arek.blackjack.controller;

import com.arek.blackjack.deck.Deck;
import com.arek.blackjack.deck.DeckService;
import com.arek.blackjack.deck.card.Card;
import com.arek.blackjack.player.Player;
import com.arek.blackjack.player.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MainController {

	private final DeckService deckService;
	private final PlayerService playerService;

	@GetMapping("/player")
	public ResponseEntity<Player> getPlayer() {
		Player player = playerService.getNewPlayerAndPersist();
		return new ResponseEntity<>(player, HttpStatus.OK);
	}

	@GetMapping("/deck")
	public ResponseEntity<Deck> getDeckOfCards() {
		Deck deck = deckService.getNewDeckAndPersist();
		return new ResponseEntity<>(deck, HttpStatus.OK);
	}

	@GetMapping("deck/{deckId}/draw")
	public ResponseEntity<Card> getCardFromDeck(@PathVariable Long deckId) {
		Deck deck = deckService.getDeckById(deckId);
		Card drawnCard = deckService.drawCardFromDeck(deck);
		return new ResponseEntity<>(drawnCard, HttpStatus.OK);
	}

	@GetMapping("deck/{deckId}/hitplayer/{playerId}")
	public ResponseEntity<Player> hitPlayerWithCardFromDeck(@PathVariable Long deckId,
	                                                        @PathVariable Long playerId) {
		Deck deck = deckService.getDeckById(deckId);
		Card drawnCard = deckService.drawCardFromDeck(deck);
		Player player = playerService.getPlayerById(playerId);
		playerService.hitPlayerWithCard(player, drawnCard);
		return new ResponseEntity<>(player, HttpStatus.OK);
	}
}
