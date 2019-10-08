package com.arek.blackjack.card;

import com.arek.blackjack.deck.Deck;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cards")
@NoArgsConstructor
public class Card implements Comparable<Card> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Enumerated(value = EnumType.STRING)
	private Suite suite;

	@Getter
	@Enumerated(value = EnumType.STRING)
	private Rank rank;

	private Card(Rank rank, Suite suite) {
		this.rank = rank;
		this.suite = suite;
	}

	public static Card of(Rank rank, Suite suite) {
		return new Card(rank, suite);
	}

	public int getValue() {
		return rank.value();
	}

	@Override
	public boolean equals(Object obj) {
		Card cardToCompare = (Card) obj;
		return (cardToCompare.rank.value() == rank.value())
				&& cardToCompare.suite.equals(suite);
	}

	@Override
	public int compareTo(Card cardToCompare) {
		return Integer.compare(rank.value(), cardToCompare.rank.value());
	}
}
