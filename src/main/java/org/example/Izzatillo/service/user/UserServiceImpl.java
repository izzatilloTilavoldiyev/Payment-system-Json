package org.example.Izzatillo.service.user;

import org.example.Izzatillo.domain.DTO.Response;
import org.example.Izzatillo.domain.DTO.SignUpDTO;
import org.example.Izzatillo.domain.model.history.History;
import org.example.Izzatillo.domain.model.user.TopUsers;
import org.example.Izzatillo.domain.model.user.User;
import org.example.Izzatillo.repository.user.UserRepository;
import org.example.Izzatillo.repository.user.UserRepositoryImpl;

import java.util.*;

public class UserServiceImpl implements UserService{
    UserRepository userRepository = UserRepositoryImpl.getInstance();

    @Override
    public Response add(SignUpDTO signUpDTO) {
        if (doesUserExists(signUpDTO.phoneNumber())) return new Response<>(
                "This user already signed Up", 400);
        User user = User.builder()
                .name(signUpDTO.name())
                .phoneNumber(signUpDTO.phoneNumber())
                .password(signUpDTO.password())
                .status(signUpDTO.userStatus())
                .build();
        user.setId(UUID.randomUUID());
        userRepository.save(user);
        return new Response<>("Successfully signed up", 200);
    }

    private boolean doesUserExists(String phoneNumber) {
        for (User user : userRepository.getAll()) {
            if (Objects.equals(user.getPhoneNumber(), phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Response signIn(String phoneNumber, String password) {
        for (User user : userRepository.getAll()) {
            if (Objects.equals(user.getPhoneNumber(), phoneNumber) &&
                    Objects.equals(user.getPassword(), password)) {
                return new Response("Success", Optional.of(user), 200);
            }
        }
        return new Response<>("User not found", Optional.empty(), 400);
    }

    @Override
    public ArrayList<User> users() {
        return userRepository.getUsers();
    }

    @Override
    public ArrayList<TopUsers> topUsers(ArrayList<History> histories) {
        ArrayList<TopUsers> topUsers = userRepository.topUsers(histories);

        Comparator<TopUsers> comparator = (a, b) -> Integer.compare(b.getTransaction(), a.getTransaction());
        topUsers.sort(comparator);
        return topUsers;
    }
}
