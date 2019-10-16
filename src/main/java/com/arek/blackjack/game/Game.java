package com.arek.blackjack.game;

import com.arek.blackjack.deck.Deck;
import com.arek.blackjack.player.Player;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

//TODO: Start implementing game logic
@Entity
public class Game {

	@Id
	Long Id;

	@OneToMany
	List<Player> players;

	@OneToOne
	Deck deck;
}
