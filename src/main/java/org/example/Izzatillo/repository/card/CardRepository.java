package org.example.Izzatillo.repository.card;

import org.example.Izzatillo.domain.DTO.Response;
import org.example.Izzatillo.domain.model.card.Card;
import org.example.Izzatillo.repository.BaseRepository;

import java.util.ArrayList;
import java.util.UUID;

public interface CardRepository extends BaseRepository<Card> {
    Response deleteCard(Card card);
    ArrayList<Card> userCardsById(UUID userId);
    Response fillBalance(Card card, Double amount);
    Response transferToCard(Card seCard, String reCard, Double amount);

}
