package org.example.Izzatillo.service.card;

import org.example.Izzatillo.domain.DTO.Response;
import org.example.Izzatillo.domain.model.card.Card;
import org.example.Izzatillo.repository.card.CardRepository;
import org.example.Izzatillo.repository.card.CardRepositoryImpl;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class CardServiceImpl implements CardService{
    CardRepository cardRepository = CardRepositoryImpl.getInstance();

    @Override
    public Response add(Card card) {
        if (doesCardExists(card.getCardNumber())) return new Response(
                "This card already created", 400);
        cardRepository.save(card);
        return new Response<>("Successfully created", 200);
    }

    private boolean doesCardExists(String cardNumber) {
        for (Card card : cardRepository.getAll()) {
            if (Objects.equals(card.getCardNumber(), cardNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Response deleteCard(Card card) {
        return cardRepository.deleteCard(card);
    }

    @Override
    public ArrayList<Card> userCardsById(UUID userId) {
        return cardRepository.userCardsById(userId);
    }

    @Override
    public Response fillBalance(Card card, Double amount) {
        return cardRepository.fillBalance(card, amount);
    }

    @Override
    public Response transferToCard(Card seCard, String reCard, Double amount) {
        return cardRepository.transferToCard(seCard, reCard, amount);
    }
}
