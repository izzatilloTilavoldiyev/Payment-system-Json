package org.example.Izzatillo.service.card;

import org.example.Izzatillo.domain.DTO.Response;
import org.example.Izzatillo.domain.model.card.Card;
import org.example.Izzatillo.service.BaseService;

import java.util.ArrayList;
import java.util.UUID;

public interface CardService extends BaseService<Card> {
    Response deleteCard(Card card);
    ArrayList<Card> userCardsById(UUID userId);
    Response fillBalance(Card card, Double amount);
    Response transferToCard(Card seCard, String reCard, Double amount);
}
