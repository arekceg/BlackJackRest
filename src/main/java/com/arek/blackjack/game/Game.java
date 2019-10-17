package com.arek.blackjack.game;

import com.arek.blackjack.deck.Deck;
import com.arek.blackjack.player.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//TODO: Start implementing game logic
@Entity
@Slf4j
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;

	@OneToMany
	private final Set<Player> players = new HashSet<>();

	@OneToOne
	@Setter
	@Getter
	private Deck deck;

	boolean addPlayer(Player playerToAdd) {
		log.info("Adding player to game");
		if (players.add(playerToAdd)) {
			log.info("Player added to game");
			return true;
		} else {
			log.error("Error adding player to game");
			return false;
		}
	}

	public List<Player> getPlayersAsList(){
		return new ArrayList<>(players);
	}
}
