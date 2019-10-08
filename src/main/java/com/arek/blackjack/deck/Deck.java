package com.arek.blackjack.deck;

import com.arek.blackjack.card.Card;
import com.arek.blackjack.card.CardService;
import com.arek.blackjack.card.Rank;
import com.arek.blackjack.card.Suite;
import exceptions.OutOfCardsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@Entity
@Component
@Table(name = "decks")
public class Deck {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private static Queue<Card> cards;
	private final CardService cardService;

	@Autowired
	private Deck(CardService cardService) {
		this.cardService = cardService;
		cards = getFullDeckOfShuffledCards();
	}

	public static Deck getNew() {
		return new Deck();
	}

	private Queue<Card> getFullDeckOfShuffledCards() {
		List<Suite> suites = Arrays.asList(Suite.values());
		List<Rank> ranks = Arrays.asList(Rank.values());
		LinkedList<Card> tempCardsList = new LinkedList<>();

		ranks.forEach(rank -> {
			suites.forEach(suite -> {
				tempCardsList.add(Card.of(rank, suite));
			});
		});
		Collections.shuffle(tempCardsList);
		return tempCardsList;
	}

	public void shuffle() {
		if (cards == null || cards.size() == 0) {
			throw new OutOfCardsException("Cannot shuffle an empty deck!");
		}

		LinkedList<Card> tempCardList = new LinkedList<>(cards);
		Collections.shuffle(tempCardList);
		cards = tempCardList;
	}

	public ArrayList<Card> getCards() {
		return new ArrayList<>(cards);
	}

	public Card getCard() {
		Optional<Card> card = Optional.ofNullable(cards.poll());
		return card.orElseThrow(() -> new OutOfCardsException("Out of cards!"));
	}
}
