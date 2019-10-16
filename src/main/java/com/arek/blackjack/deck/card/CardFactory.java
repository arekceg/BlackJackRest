package com.arek.blackjack.deck.card;

interface CardFactory {
	Card getCard(Rank rank, Suite suite);
}
