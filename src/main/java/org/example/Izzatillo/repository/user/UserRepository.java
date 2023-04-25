package org.example.Izzatillo.repository.user;

import org.example.Izzatillo.domain.model.history.History;
import org.example.Izzatillo.domain.model.user.TopUsers;
import org.example.Izzatillo.domain.model.user.User;
import org.example.Izzatillo.repository.BaseRepository;

import java.util.ArrayList;

public interface UserRepository extends BaseRepository<User> {
    ArrayList<TopUsers> topUsers(ArrayList<History> histories);
}
