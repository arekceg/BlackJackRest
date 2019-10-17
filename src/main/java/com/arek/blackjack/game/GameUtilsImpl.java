package com.arek.blackjack.game;

import com.arek.blackjack.player.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@AllArgsConstructor
@Transactional
public class GameUtilsImpl implements GameUtils {

	private final Game game;

	@Override
	public boolean addPlayer(Player player) {
		return game.addPlayer(player);
	}

	@Override
	public void persistPlayersAndDeck() {

	}

	@Override
	public void startWithPlayers(int amountOfPlayers) {

	}
}
