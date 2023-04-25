package org.example.Izzatillo.domain.DTO;

import org.example.Izzatillo.domain.model.user.UserStatus;

public record SignUpDTO(String name, String phoneNumber, String password, UserStatus userStatus) {
}
