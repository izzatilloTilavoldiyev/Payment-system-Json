package org.example.Izzatillo.repository.card;

import com.google.gson.reflect.TypeToken;
import org.example.Izzatillo.domain.DTO.Response;
import org.example.Izzatillo.domain.model.card.Card;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.example.Izzatillo.util.BeanUtils.gson;

public class CardRepositoryImpl implements CardRepository{
    private final static CardRepositoryImpl instance = new CardRepositoryImpl();

    private CardRepositoryImpl() {

    }

    public static CardRepositoryImpl getInstance() {
        return instance;
    }

    String path = "src/main/resources/Cards.json";
    @Override
    public void save(Card card) {
        ArrayList<Card> cards = getAll();
        cards.add(card);
        update(cards);
    }

    @Override
    public ArrayList<Card> getAll() {
        return readFromFile(path);
    }

    @Override
    public ArrayList<Card> readFromFile(String path) {
        try {
            FileReader reader = new FileReader(path);
            Type type = new TypeToken<ArrayList<Card>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(UUID id) {

    }

    @Override
    public void remove(Card card) {

    }

    @Override
    public Response deleteCard(Card card) {
        ArrayList<Card> cards = getAll();
        for (Card card1 : cards) {
            if (Objects.equals(card1.getId(), card.getId())) {
                card1.setActive(false);
                update(cards);
                return new Response<>("Success", 200);
            }
        }
        return new Response<>("Card not found", 400);
    }

    @Override
    public ArrayList<Card> userCardsById(UUID userId) {
        ArrayList<Card> userCardsById = new ArrayList<>();
        for (Card card : getAll()) {
            if (Objects.equals(card.isActive(), true) && Objects.equals(card.getId(), userId)) {
                userCardsById.add(card);
            }
        }
        return userCardsById;
    }

    @Override
    public Response fillBalance(Card card, Double amount) {
        ArrayList<Card> cards = getAll();
        for (Card card1 : cards) {
            if (Objects.equals(card1.getId(), card.getId())) {
                card1.setBalance(card.getBalance() + amount);
            }
        }
        update(cards);
        return new Response<>("Success", 200);
    }

    @Override
    public Response transferToCard(Card seCard, String reCard, Double amount) {
        if (seCard.getBalance() < amount) return new Response<>(
                "You don't have enough money... please fill your balance", Optional.empty(), 401);
        ArrayList<Card> cards = getAll();
        for (Card card : cards) {
            if (Objects.equals(card.getCardNumber(), reCard)) {
                card.setBalance(card.getBalance() + amount);
                for (Card card1 : cards) {
                    if (Objects.equals(card1.getId(), seCard.getId()))
                        card1.setBalance(seCard.getBalance() - amount);
                }
                update(cards);
                return new Response<>("Successfully transferred", card, 200);
            }
        }
        return new Response<>("Card not found", null, 400);
    }

    @Override
    public void update(ArrayList<Card> cards) {
        try {
            PrintWriter writer = new PrintWriter(path);
            gson.toJson(cards, writer);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
