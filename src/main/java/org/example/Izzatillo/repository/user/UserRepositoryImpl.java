package org.example.Izzatillo.repository.user;

import com.google.gson.reflect.TypeToken;
import org.example.Izzatillo.domain.model.history.History;
import org.example.Izzatillo.domain.model.user.TopUsers;
import org.example.Izzatillo.domain.model.user.User;
import org.example.Izzatillo.domain.model.user.UserStatus;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import static org.example.Izzatillo.util.BeanUtils.gson;

public class UserRepositoryImpl implements UserRepository{
    private final static UserRepositoryImpl instance = new UserRepositoryImpl();
    String path = "src/main/resources/Users.json";

    private UserRepositoryImpl() {

    }

    public static UserRepositoryImpl getInstance() {
        return instance;
    }

    @Override
    public void save(User user) {
        ArrayList<User> users = getAll();
        users.add(user);
        update(users, path);
    }

    @Override
    public ArrayList<User> getAll() {
        return readFromFile(path);
    }

    @Override
    public ArrayList<User> readFromFile(String path) {
        try {
            FileReader reader = new FileReader(path);
            Type type = new TypeToken<ArrayList<User>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(UUID id) {

    }

    @Override
    public void remove(User user) {

    }

    @Override
    public ArrayList<TopUsers> topUsers(ArrayList<History> histories) {
        ArrayList<TopUsers> topUsers = new ArrayList<>();
        int count = 0;
        for (User user : getAll()) {
            for (History history : histories) {
                if (Objects.equals(user.getId(), history.getId())) {
                    count++;
                }
            }
            if (count != 0) {
                TopUsers user1 = new TopUsers(user, count);
                topUsers.add(user1);
            }
            if (topUsers.size() == 5) {
                break;
            }
            count = 0;
        }
        return topUsers;
    }

    @Override
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (User user : getAll()) {
            if (!Objects.equals(user.getStatus(), UserStatus.ADMIN)) {
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public void update(ArrayList<User> users, String path) {
        try {
            PrintWriter writer = new PrintWriter(path);
            gson.toJson(users, writer);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
