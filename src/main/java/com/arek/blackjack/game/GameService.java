package com.arek.blackjack.game;

import com.arek.blackjack.deck.DeckService;
import com.arek.blackjack.exceptions.BlackJackException;
import com.arek.blackjack.player.PlayerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class GameService {

	private final EntityManager em;
	private final DeckService deckService;
	private final PlayerService playerService;
	private final GameRepository gameRepository;
	private final GameFactory gameFactory;

	public Game createAndPersistGameWithPlayers(int amountOfPlayers) {
		log.info("Creating and persisting new game");
		Game newGame = gameFactory.newGame(amountOfPlayers);
		Game savedGame = persistAndReturnPersistedGame(newGame);
		log.info("Game persisted. ID: " + savedGame.getId());
		return savedGame;
	}

	private Game persistAndReturnPersistedGame(Game game) {
		game.getPlayersAsList().forEach(playerService::persistPlayer);
		deckService.persistDeck(game.getDeck());
		return gameRepository.save(game);
	}

	public Game getGameById(Long gameId){
		EntityGraph gameEntityGraph = em.getEntityGraph("graph.Game.players");
		Optional<Game> foundGame = Optional.ofNullable(em.find(Game.class, 1L,
				Collections.singletonMap("javax.persistence.fetchgraph", gameEntityGraph)));
		return foundGame.orElseThrow(() -> new BlackJackException("Game with ID " + gameId.toString() + " not found!" ));
	}

	public Game getGameByIdLazy(Long gameId){
		return gameRepository.findById(gameId).orElseThrow(
				() -> new BlackJackException("Game with ID " + gameId.toString() + " not found!" ));
	}

}
