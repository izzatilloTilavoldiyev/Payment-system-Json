package org.example.Izzatillo.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopUsers {
    private User user;
    private Integer transaction;

    @Override
    public String toString() {
        return user.getName() + " || " + user.getPhoneNumber() + " | " + "transaction -> " + transaction;
    }
}
