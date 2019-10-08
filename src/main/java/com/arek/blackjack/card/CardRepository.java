package com.arek.blackjack.card;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CardRepository extends JpaRepository<Card,Long> {

	public Card findCardByRankAndSuite(Rank rank, Suite suite);

}
