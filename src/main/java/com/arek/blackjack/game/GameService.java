package com.arek.blackjack.game;

import com.arek.blackjack.deck.DeckService;
import com.arek.blackjack.player.PlayerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class GameService {

	private final DeckService deckService;
	private final PlayerService playerService;
	private final GameRepository gameRepository;
	private final GameFactory gameFactory;

	public Game createAndPersistGameWithPlayers(int amountOfPlayers) {
		log.info("Creating and persisting new game");
		Game newGame = gameFactory.newGame(amountOfPlayers);
		deckService.persistDeck(newGame.getDeck());
		newGame.getPlayersAsList().forEach(playerService::persistPlayer);
		Game savedGame = gameRepository.save(newGame);
		log.info("Game persisted. ID: " + savedGame.getId());
		return savedGame;
	}

}
