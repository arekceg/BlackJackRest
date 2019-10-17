package com.arek.blackjack.game;

import com.arek.blackjack.deck.DeckFactory;
import com.arek.blackjack.player.PlayerFactory;
import com.arek.blackjack.player.PlayerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;

@Component
@AllArgsConstructor
@Slf4j
public class GameFactoryImpl implements GameFactory {

	private final PlayerFactory playerFactory;
	private final DeckFactory deckFactory;

	@Override
	public Game newGame(int amountOfPlayers) {
		log.info("Creating new game with " + amountOfPlayers + " players");
		Game newGame = new Game();
		for (int i = 0; i < amountOfPlayers; i++) {
			newGame.addPlayer(playerFactory.newPlayer());
		}
		newGame.setDeck(deckFactory.getNewDeck());
		log.info("New game created");
		return newGame;
	}
}
