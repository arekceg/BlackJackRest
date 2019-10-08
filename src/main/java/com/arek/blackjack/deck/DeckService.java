package com.arek.blackjack.deck;

import com.arek.blackjack.card.Card;
import com.arek.blackjack.card.CardService;
import exceptions.BlackJackException;
import exceptions.OutOfCardsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeckService {
	private final DeckRepository deckRepository;
	private final CardService cardService;

	public void saveDeck(Deck deck) {
		deckRepository.save(deck);
	}

	public Deck findDeckById(Long deckId) {
		return deckRepository.findById(deckId).orElseThrow(() ->
				new BlackJackException("No deck found by id: " + deckId.toString()));
	}

	public Card drawCardFromDeckByDeckId(Long deckId) {
		Long firstCardId = getFirstCardIdFromDeckByDeckId(deckId).orElseThrow(() ->
				new OutOfCardsException("Deck is out of cards!"));
		deckRepository.removeCardFromDeckByDeckId(deckId, firstCardId);
		return cardService.getCardById(firstCardId);
	}

	private Optional<Long> getFirstCardIdFromDeckByDeckId(Long deckId) {
		Optional<Long> cardIdOptional =
				Optional.ofNullable(deckRepository.getFirstCardIdFromDeckByDeckId(deckId));
		return cardIdOptional;
	}
}
