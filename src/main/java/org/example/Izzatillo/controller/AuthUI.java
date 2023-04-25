package org.example.Izzatillo.controller;

import org.example.Izzatillo.domain.DTO.Response;
import org.example.Izzatillo.domain.DTO.SignUpDTO;
import org.example.Izzatillo.domain.model.user.User;
import org.example.Izzatillo.domain.model.user.UserStatus;

import java.util.Optional;

import static org.example.Izzatillo.util.BeanUtils.*;

public class AuthUI {
    public void startProject() {

        while (true) {
            System.out.println("1. Sign In     2. Sign Up     0. Exit");
            int action;
            action = scanNum.nextInt();
            switch (action) {
                case 1 ->
                        signIn();

                case 2 ->
                        signUp();

                case 0 -> {
                    System.out.println("Thank You!"); return;
                }
            }
        }
    }

    private void signIn() {
        System.out.print("Enter phone number: ");
        String phoneNumber = scanStr.nextLine();

        System.out.print("Enter password: ");
        String password = scanStr.nextLine();

        Response response = userService.signIn(phoneNumber, password);
        Optional<User> user = (Optional<User>) response.getData();
        if (user.isEmpty()) System.out.println(response.getMessage());
        else {
            User user1 = user.get();
            switch (user1.getStatus()) {
                case USER -> userController.userMenu(user1);
                case ADMIN -> adminController.adminMenu(user1);
            }
        }
    }

    private void signUp() {
        while (true) {
            System.out.println("1. User     2. Admin     0. Exit");
            int action = scanNum.nextInt();
            switch (action) {
                case 1 -> {
                    SignUpDTO userDTO = getInfo(UserStatus.USER);
                    Response response = userService.add(userDTO);
                    System.out.println(response.getMessage());
                    if (response.getStatus() == 200) {
                        return;
                    }
                }
                case 2 -> {
                    SignUpDTO userDTO = getInfo(UserStatus.ADMIN);
                    Response response = userService.add(userDTO);
                    System.out.println(response.getMessage());
                    if (response.getStatus() == 200) {
                        return;
                    }
                }
                case 0 -> {
                    System.out.println("Thank you"); return;
                }
                default -> System.out.println("Wrong input... try again");

            }
        }
    }

    private SignUpDTO getInfo(UserStatus userStatus) {
        System.out.print("Enter name: ");
        String name = scanStr.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanStr.nextLine();

        System.out.print("Enter password: ");
        String password = scanStr.nextLine();

        SignUpDTO userDTO = new SignUpDTO(name, phoneNumber, password, userStatus);
        return userDTO;
    }
}
