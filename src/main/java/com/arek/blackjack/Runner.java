package com.arek.blackjack;

import com.arek.blackjack.card.CardService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Runner implements CommandLineRunner {
	private final CardService cardService;

	@Override
	public void run(String... args) throws Exception {
		cardService.initCards();
	}
}
