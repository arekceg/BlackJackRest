package com.arek.blackjack.deck.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.arek.blackjack.deck.card.Rank.JACK;

public enum Card {

	TWO_HEARTS(Rank.TWO, Suite.HEARTS),
	THREE_HEARTS(Rank.THREE, Suite.HEARTS),
	FOUR_HEARTS(Rank.FOUR, Suite.HEARTS),
	FIVE_HEARTS(Rank.FIVE, Suite.HEARTS),
	SIX_HEARTS(Rank.SIX, Suite.HEARTS),
	SEVEN_HEARTS(Rank.SEVEN, Suite.HEARTS),
	EIGHT_HEARTS(Rank.EIGHT, Suite.HEARTS),
	NINE_HEARTS(Rank.NINE, Suite.HEARTS),
	TEN_HEARTS(Rank.TEN, Suite.HEARTS),
	JACK_HEARTS(JACK, Suite.HEARTS),
	QUEEN_HEARTS(Rank.QUEEN, Suite.HEARTS),
	KING_HEARTS(Rank.KING, Suite.HEARTS),
	ACE_HEARTS(Rank.ACE, Suite.HEARTS),
	TWO_SPADES(Rank.TWO, Suite.HEARTS),
	THREE_SPADES(Rank.THREE, Suite.SPADES),
	FOUR_SPADES(Rank.FOUR, Suite.SPADES),
	FIVE_SPADES(Rank.FIVE, Suite.SPADES),
	SIX_SPADES(Rank.SIX, Suite.SPADES),
	SEVEN_SPADES(Rank.SEVEN, Suite.SPADES),
	EIGHT_SPADES(Rank.EIGHT, Suite.SPADES),
	NINE_SPADES(Rank.NINE, Suite.SPADES),
	TEN_SPADES(Rank.TEN, Suite.SPADES),
	JACK_SPADES(JACK, Suite.SPADES),
	QUEEN_SPADES(Rank.QUEEN, Suite.SPADES),
	KING_SPADES(Rank.KING, Suite.SPADES),
	ACE_SPADES(Rank.ACE, Suite.SPADES),
	TWO_CLUBS(Rank.TWO, Suite.CLUBS),
	THREE_CLUBS(Rank.THREE, Suite.CLUBS),
	FOUR_CLUBS(Rank.FOUR, Suite.CLUBS),
	FIVE_CLUBS(Rank.FIVE, Suite.CLUBS),
	SIX_CLUBS(Rank.SIX, Suite.CLUBS),
	SEVEN_CLUBS(Rank.SEVEN, Suite.CLUBS),
	EIGHT_CLUBS(Rank.EIGHT, Suite.CLUBS),
	NINE_CLUBS(Rank.NINE, Suite.CLUBS),
	TEN_CLUBS(Rank.TEN, Suite.CLUBS),
	JACK_CLUBS(JACK, Suite.CLUBS),
	QUEEN_CLUBS(Rank.QUEEN, Suite.CLUBS),
	KING_CLUBS(Rank.KING, Suite.CLUBS),
	ACE_CLUBS(Rank.ACE, Suite.CLUBS),
	TWO_DIAMONDS(Rank.TWO, Suite.DIAMONDS),
	THREE_DIAMONDS(Rank.THREE, Suite.DIAMONDS),
	FOUR_DIAMONDS(Rank.FOUR, Suite.DIAMONDS),
	FIVE_DIAMONDS(Rank.FIVE, Suite.DIAMONDS),
	SIX_DIAMONDS(Rank.SIX, Suite.DIAMONDS),
	SEVEN_DIAMONDS(Rank.SEVEN, Suite.DIAMONDS),
	EIGHT_DIAMONDS(Rank.EIGHT, Suite.DIAMONDS),
	NINE_DIAMONDS(Rank.NINE, Suite.DIAMONDS),
	TEN_DIAMONDS(Rank.TEN, Suite.DIAMONDS),
	JACK_DIAMONDS(JACK, Suite.DIAMONDS),
	QUEEN_DIAMONDS(Rank.QUEEN, Suite.DIAMONDS),
	KING_DIAMONDS(Rank.KING, Suite.DIAMONDS),
	ACE_DIAMONDS(Rank.ACE, Suite.DIAMONDS);

	Suite suite;
	Rank rank;
	int value;

	Card(Rank rank, Suite suite) {
		this.rank = rank;
		this.suite = suite;
		value = rank.getValue();
	}

	public static List<Card> getAllCards() {
		return new ArrayList<>(Arrays.asList(Card.values()));
	}

	public int getValue() {
		return value;
	}
}
