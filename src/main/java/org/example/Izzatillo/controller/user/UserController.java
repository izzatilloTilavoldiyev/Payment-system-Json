package org.example.Izzatillo.controller.user;

import org.example.Izzatillo.controller.CardUI;
import org.example.Izzatillo.domain.DTO.Response;
import org.example.Izzatillo.domain.model.card.Card;
import org.example.Izzatillo.domain.model.history.History;
import org.example.Izzatillo.domain.model.history.HistoryStatus;
import org.example.Izzatillo.domain.model.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.example.Izzatillo.util.BeanUtils.*;

public class UserController {
    CardUI cardUI = new CardUI();

    public void userMenu(User user) {
        while (true) {
            System.out.println("1. Transfer     2. My cards     3. History     0. Exit");
            int action = scanNum.nextInt();
            switch (action) {
                case 1 -> transfer(user);
                case 2 -> cardUI.userCard(user);
                case 3 -> history(user);
                case 0 -> {
                    System.out.println("Thank you for using!");
                    return;
                }
            }
        }
    }

    private void transfer(User user) {
        while (true) {
            System.out.println("1. Transfer to card     0. Back");
            int action = scanNum.nextInt();
            switch (action) {
                case 1 -> transferToCard(user);
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void transferToCard(User user) {
        if (cardUI.myCards(user) == -1) return;
        int choose = scanNum.nextInt();
        try {
            Card card = cardService.userCardsById(user.getId()).get(choose - 1);
            System.out.println("Your card -> " + card.getCardNumber());

            System.out.print("Card number: ");
            String cardNumber = scanStr.nextLine();

            System.out.print("Amount: ");
            Double amount = scanNum.nextDouble();
            Response response = cardService.transferToCard(card, cardNumber, amount);
            System.out.println(response);
            if (response.getStatus() == 200) {
                Card reCard = (Card) response.getData();
                LocalDateTime localDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

                History history = new History(HistoryStatus.Outcome, reCard.getId(), card.getCardNumber(),
                        cardNumber, 0.0, amount, localDateTime.format(formatter));
                history.setId(user.getId());
                historyService.add(history);
            }
        } catch (Exception e) {
            System.out.println("Wrong input... try again");
        }
    }

    private void history(User user) {
        while (true) {
            System.out.println("1. All     2. Outcome     3. Income     0. Back");
            int action = scanNum.nextInt();
            switch (action) {
                case 1 -> {
                    int i = 1;
                    for (History history : historyService.getUserHistories(user.getId())) {
                        System.out.println(i++ + ":\n" + history);
                    }
                }
                case 2 -> {
                    int i = 1;
                    for (History history : historyService.userOutcomeHistories(user.getId())) {
                        System.out.println(i++ + ":\n" + history);
                    }
                }
                case 3 -> {
                    int i = 1;
                    for (History history : historyService.userIncomeHistories(user.getId())) {
                        System.out.println(i++ + ":\n" + history);
                    }
                }
                case 0 -> {
                    System.out.println("Thank you!");
                    return;
                }
            }
        }

    }

}
