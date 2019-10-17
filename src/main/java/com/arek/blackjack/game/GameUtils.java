package com.arek.blackjack.game;

import com.arek.blackjack.player.Player;

public interface GameUtils {
	boolean addPlayer(Player player);
	void startWithPlayers(int amountOfPlayers);
	void persistPlayersAndDeck();

}
