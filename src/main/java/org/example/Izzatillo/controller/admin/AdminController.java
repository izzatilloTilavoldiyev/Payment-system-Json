package org.example.Izzatillo.controller.admin;

import org.example.Izzatillo.domain.model.history.History;
import org.example.Izzatillo.domain.model.user.TopUsers;
import org.example.Izzatillo.domain.model.user.User;

import java.util.ArrayList;

import static org.example.Izzatillo.util.BeanUtils.*;

public class AdminController {
    public void adminMenu(User user) {
        while (true) {
            System.out.println("1. All transactions\n2. User's transaction\n3. Between period\n4. Top users\n0. Exit");
            int action = scanNum.nextInt();
            switch (action) {
                case 1 -> allTransactions();
                case 2 -> userTransaction();
                case 3 -> betweenPeriod();
                case 4 -> topUsers();
                case 0 -> {
                    System.out.println("Thank you!"); return;
                }
            }
        }
    }

    private void allTransactions() {
        int i = 1;
        for (History history : historyService.getAllHistories()) {
            System.out.println(i++ + ":\n" + history);
        }
    }

    private void userTransaction() {
        ArrayList<User> users = userService.users();
        int i = 1;
        for (User user : users) {
            System.out.println(i++ + " -> " + user);
        }
        try {
            int choose = scanNum.nextInt();
            User user = users.get(choose - 1);
            try {
                int j = 1;
                for (History userHistory : historyService.getUserHistories(user.getId())) {
                    System.out.println(j++ + ":\n" + userHistory);
                }
            }catch (Exception e) {
                System.out.println("There is no history yet in this user, see other users'");
            }
        }catch (Exception e) {
            System.out.println("Wrong input... try again");
        }

    }
    private void betweenPeriod() {
        System.out.println("You must enter date like this ->  " + "dd-MM-yyyy");

        System.out.print("First date: ");
        String firstDate = scanStr.nextLine();

        System.out.print("Last date: ");
        String lastDate = scanStr.nextLine();

        try {
            ArrayList<History> histories = historyService.historiesByPeriod(firstDate, lastDate);
            int i = 1;
            for (History history : histories) {
                System.out.println(i++ + ":\n" + history);
            }
        }catch (Exception e) {
            System.out.println("Something went wrong... try again");
        }

    }

    private void topUsers() {
        while (true) {
            System.out.println("1. In a week     2. In a month     0. Back");
            int action = scanNum.nextInt();
            switch (action) {
                case 1 -> {
                    displayTopUsers(userService.topUsers(historyService.historiesWeek()));
                    System.out.println(historyService.historiesWeek().size());
                }
                case 2 -> {
                    displayTopUsers(userService.topUsers(historyService.historiesMonth()));
                    System.out.println(historyService.historiesMonth().size());
                }
                case 0 -> {
                    System.out.println("Thank you!"); return;
                }
            }
        }
    }

    private void displayTopUsers(ArrayList<TopUsers> topUsers) {
        int i = 1;
        System.out.println("             Top 5 users");
        System.out.println(topUsers.size());
        for (TopUsers user : topUsers) {
            System.out.println(i++ + " -> " + user);
        }
    }
}
