package com.arek.blackjack.deck;

import com.arek.blackjack.card.Card;
import com.arek.blackjack.card.CardService;
import exceptions.OutOfCardsException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
	@Getter
	private Long id;

	@ManyToMany
	@JoinTable(name = "decks_cards",
			joinColumns = @JoinColumn(name = "deck_id"),
			inverseJoinColumns = @JoinColumn(name = "card_id"))
	private final List<Card> cards = new ArrayList<>();

	@Autowired
	public Deck(DeckService deckService, CardService cardService) {
		this.deckService = deckService;
		this.cardService = cardService;
		fillDeckWithCards(cardService.getAllCards());
	}

	public void fillDeckWithCards(List<Card> inputCards) {
		Collections.shuffle(inputCards);
		cards.addAll(inputCards);
	}

	public void shuffle() {
		if (cards == null || cards.size() == 0) {
			throw new OutOfCardsException("Cannot shuffle an empty deck!");
		}
		Collections.shuffle(cards);
	}


	public ArrayList<Card> getCardsAsList() {
		return new ArrayList<>(cards);
	}

	public Card getCardFromDeck() {
		return deckService.drawCardFromDeckByDeckId(id);
	}
}
