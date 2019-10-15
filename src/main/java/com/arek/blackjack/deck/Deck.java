package com.arek.blackjack.deck;

import com.arek.blackjack.card.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "decks")
@NoArgsConstructor
public class Deck {

	@ElementCollection
	@CollectionTable(name = "decks_cards",
			joinColumns = @JoinColumn(name = "deck_id"))
	final Set<Card> cards = new LinkedHashSet<>();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;

	public void shuffle() {
		if (cards.size() == 0) {
			throw new OutOfCardsException("Deck empty! Cannot shuffle an empty deck!");
		}
		List<Card> cardsList = new ArrayList<>(cards);
		Collections.shuffle(cardsList);
		cards.clear();
		cards.addAll(cardsList);
	}

	public List<Card> getCardsAsList(){
		return Collections.unmodifiableList(new ArrayList<>(cards));
	}

	public Card drawCardFromDeck() {
			Card drawnCard = cards.stream().findFirst().orElseThrow(() ->
					new OutOfCardsException("Deck is out of cards!"));
			cards.remove(drawnCard);
			return drawnCard;
	}
}