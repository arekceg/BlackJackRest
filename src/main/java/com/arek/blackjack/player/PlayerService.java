package com.arek.blackjack.player;

import com.arek.blackjack.deck.card.Card;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class PlayerService {

	private final PlayerFactory playerFactory;
	private final PlayerRepository playerRepository;

	public Player persistPlayer(Player player){
		log.info("Persisting player via PlayerService");
		return playerRepository.save(player);
	}

	public Player getNewPlayerAndPersist() {
		log.info("Creating and persisting new player");
		Player newPlayer = playerFactory.newPlayer();
		Player savedPlayer = playerRepository.save(newPlayer);
		log.info("Persisted player. ID: " + savedPlayer.getId());
		return savedPlayer;
	}

	public void hitPlayerWithCard(Player player, Card card) {
		player.hit(card);
	}


	public Player getPlayerById(Long playerId) {
		return playerRepository.findById(playerId).orElseThrow(() ->
				new PlayerNotFoundException("Player with ID " + playerId.toString() + " not found"));
	}
}
