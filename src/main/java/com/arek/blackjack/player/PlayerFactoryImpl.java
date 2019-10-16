package com.arek.blackjack.player;

import com.arek.blackjack.deck.Deck;
import com.arek.blackjack.deck.DeckFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlayerFactoryImpl implements PlayerFactory {

	@Override
	public Player newPlayer() {
		return new Player();
	}
}
