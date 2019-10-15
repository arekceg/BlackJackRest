package com.arek.blackjack.card;

public interface CardFactory {
	Card getCard(Rank rank, Suite suite);
}
