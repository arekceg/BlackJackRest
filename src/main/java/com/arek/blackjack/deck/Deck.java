package com.arek.blackjack.deck;

import com.arek.blackjack.card.Card;
import com.arek.blackjack.card.CardService;
import exceptions.OutOfCardsException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "decks")
@NoArgsConstructor
public class Deck {
	@Transient
	private DeckService deckService;
	@Transient
	private CardService cardService;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany
	@JoinTable(name = "decks_cards",
			joinColumns = @JoinColumn(name = "deck_id"),
			inverseJoinColumns = @JoinColumn(name = "card_id"))
	private List<Card> cards;


	@Autowired
	public Deck(DeckService deckService, CardService cardService) {
		this.deckService = deckService;
		this.cardService = cardService;
		cards = getFullDeckOfShuffledCards();
	}

	private List<Card> getFullDeckOfShuffledCards() {
		List<Card> cards = cardService.getAllCards();
		Collections.shuffle(cards);
		return cards;
	}

	public void shuffle() {
		if (cards == null || cards.size() == 0) {
			throw new OutOfCardsException("Cannot shuffle an empty deck!");
		}

		LinkedList<Card> tempCardList = new LinkedList<>(cards);
		Collections.shuffle(tempCardList);
		cards = tempCardList;
	}

	public ArrayList<Card> getCardsAsList() {
		return new ArrayList<>(cards);
	}

	public Card getCardFromDeck() {
		return deckService.drawCardFromDeckByDeckId(id);
	}
}
