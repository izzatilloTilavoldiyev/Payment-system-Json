package org.example.Izzatillo.service.user;

import org.example.Izzatillo.domain.DTO.Response;
import org.example.Izzatillo.domain.DTO.SignUpDTO;
import org.example.Izzatillo.domain.model.history.History;
import org.example.Izzatillo.domain.model.user.TopUsers;
import org.example.Izzatillo.domain.model.user.User;
import org.example.Izzatillo.service.BaseService;

import java.util.ArrayList;

public interface UserService extends BaseService<SignUpDTO> {
    Response signIn(String phoneNumber, String password);
    ArrayList<User> users();
    ArrayList<TopUsers> topUsers(ArrayList<History> histories);

}
