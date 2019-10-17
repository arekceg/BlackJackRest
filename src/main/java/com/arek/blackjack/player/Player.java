package com.arek.blackjack.player;

import com.arek.blackjack.deck.card.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Slf4j
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	long id;

	@ElementCollection
	private final Set<Card> cards = new HashSet<>();

	private int valueOfCards = 0;

	void hit(Card drawnCard) {
		cards.add(drawnCard);
		valueOfCards += drawnCard.getValue();
	}

	public int getValueOfCards() {
		return valueOfCards;
	}

	List<Card> getCardsAsList() {
		return Collections.unmodifiableList(new ArrayList<>(cards));
	}

}
