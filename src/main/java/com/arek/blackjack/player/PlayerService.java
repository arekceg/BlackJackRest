package com.arek.blackjack.player;

import com.arek.blackjack.deck.card.Card;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class PlayerService {

	private final PlayerFactory playerFactory;
	private final PlayerRepository playerRepository;

	public Player getNewPlayerAndPersist(){
		Player newPlayer = playerFactory.newPlayer();
		return playerRepository.save(newPlayer);
	}

	public void hitPlayerWithCard(Player player, Card card){
		player.hit(card);
	}


	public Player getPlayerById(Long playerId) {
		return playerRepository.findById(playerId).orElseThrow(() ->
				new PlayerNotFoundException("Player not found"));
	}
}
