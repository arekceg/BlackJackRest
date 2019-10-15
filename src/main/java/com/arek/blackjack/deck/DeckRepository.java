package com.arek.blackjack.deck;

import com.arek.blackjack.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface DeckRepository extends JpaRepository<Deck,Long> {

}
