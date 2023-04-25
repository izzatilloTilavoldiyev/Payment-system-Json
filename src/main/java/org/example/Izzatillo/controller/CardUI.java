package org.example.Izzatillo.controller;

import org.example.Izzatillo.domain.DTO.Response;
import org.example.Izzatillo.domain.model.card.Card;
import org.example.Izzatillo.domain.model.card.CardStatus;
import org.example.Izzatillo.domain.model.user.User;

import java.util.ArrayList;
import java.util.UUID;

import static org.example.Izzatillo.util.BeanUtils.*;

public class CardUI {
    public void userCard(User user) {
        while (true) {
            System.out.println("1. Add card     2. Fill balance     3. My cards     4. Delete card     0. Exit");
            int action = scanNum.nextInt();
            switch (action) {
                case 1 -> addCard(user);
                case 2 -> fillBalance(user);
                case 3 -> myCards(user);
                case 4 -> deleteCard(user);
                case 0 -> {
                    System.out.println("Thank you!");
                    return;
                }
            }
        }
    }

    private void addCard(User user) {
        while (true) {
            System.out.println("1. HUMO     2. UzCard     0. Back");
            int action = scanNum.nextInt();
            switch (action) {
                case 1 -> {
                    Response response = add(user.getId(), CardStatus.HUMO);
                    System.out.println(response);
                    if (response.getStatus() == 200) return;
                }
                case 2 -> {
                    Response response = add(user.getId(), CardStatus.UzCard);
                    System.out.println(response);
                    if (response.getStatus() == 200) return;
                }

                case 0 -> {
                    System.out.println("Thank you!"); return;
                }
            }
        }
    }

    private void fillBalance(User user) {
        if (myCards(user) == -1) return;

        int choose = scanNum.nextInt();
        try {
            Card card = cardService.userCardsById(user.getId()).get(choose - 1);
            System.out.println("Card number -> " + card.getCardNumber());
            System.out.print("Amount: ");
            Double amount = scanStr.nextDouble();
            System.out.println(cardService.fillBalance(card, amount));
        } catch (Exception e) {
            System.out.println("Wrong input... try again");
        }
    }

    public Integer myCards(User user) {
        ArrayList<Card> userCards = cardService.userCardsById(user.getId());
        if (userCards.isEmpty()) {
            System.out.println("There is no cards yet, please add card"); return -1;
        }
        int i = 1;
        for (Card card : userCards) {
            System.out.println(i++ + ":\n" + card);
        }
        return 1;
    }

    private Response add(UUID userId, CardStatus cardStatus) {
        System.out.print("Card Number: ");
        String cardNumber = scanStr.nextLine();
        Card card = new Card(cardStatus, cardNumber, 0.0, true);
        card.setId(userId);
        return cardService.add(card);
    }

    private void deleteCard(User user) {
        if (myCards(user) == -1) return;
        int choose = scanNum.nextInt();

        try {
            Card card = cardService.userCardsById(user.getId()).get(choose - 1);
            System.out.println(cardService.deleteCard(card));
        }catch (Exception e) {
            System.out.println("Wrong input... try again");
        }

    }
}
