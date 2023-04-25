package org.example.Izzatillo.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.Izzatillo.domain.model.BaseModel;

@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class User extends BaseModel {
    private String name;
    private String phoneNumber;
    private String password;
    private UserStatus status;

    @Override
    public String toString() {
        return name + " | " + phoneNumber;
    }
}
