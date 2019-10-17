package com.arek.blackjack.game;

public interface GameFactory {
	Game newGame(int amountOfPlayers);
}
