package com.arek.blackjack.deck;

import com.arek.blackjack.card.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.scaffold.MethodGraph;

import javax.persistence.*;
import java.util.*;

//TODO: Mapowanie na bazę danych listę stringów w hibernate wygooglować @ElementCollection
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

	//TODO: MOŻLIWE że hibernate wywołuje metody z *get* bo myśli że to getter
	public Card drawCardFromDeck() {
			Card drawnCard = cards.stream().findFirst().orElseThrow(() ->
					new OutOfCardsException("Deck is out of cards!"));
			cards.remove(drawnCard);
			return drawnCard;
	}
}