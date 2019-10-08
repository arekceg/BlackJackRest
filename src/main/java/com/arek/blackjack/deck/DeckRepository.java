package com.arek.blackjack.deck;

import com.arek.blackjack.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface DeckRepository extends JpaRepository<Deck,Long> {
	@Query(value = "SELECT CARD_ID FROM DECKS_CARDS WHERE DECK_ID = ?1 LIMIT 1", nativeQuery = true)
	Long getFirstCardIdFromDeckByDeckId(Long deckId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM DECKS_CARDS WHERE DECK_ID = ?1 AND CARD_ID = ?2", nativeQuery = true)
	void removeCardFromDeckByDeckId(Long deckId, Long cardId);
}
