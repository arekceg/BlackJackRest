package com.arek.blackjack.card;

import exceptions.CardNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CardService {

	private final CardRepository cardRepository;


	public void initCards(){
		List<Suite> suites = Arrays.asList(Suite.values());
		List<Rank> ranks = Arrays.asList(Rank.values());

		ranks.forEach(rank -> {
			suites.forEach(suite -> {
				saveCardToDatabase(Card.of(rank, suite));
			});
		});
	}

	public void saveCardToDatabase(Card card){
		cardRepository.save(card);
	}

	public Card getCard(Rank rank, Suite suite){
		Optional<Card> cardOptional
				= Optional.ofNullable(cardRepository.findCardByRankAndSuite(rank,suite));
		return cardOptional.orElseThrow(() -> new CardNotFoundException("Card not found!"));
	}
}
