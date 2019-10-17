package com.arek.blackjack.player;

import com.arek.blackjack.deck.Deck;
import com.arek.blackjack.deck.DeckFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PlayerFactoryImpl implements PlayerFactory {

	@Override
	public Player newPlayer() {
		log.info("Creating new player");
		return new Player();
	}
}
