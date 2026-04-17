package com.example.schedule_app.dto;

import com.example.schedule_app.entity.User;
import lombok.Getter;

@Getter
public class LoginResponse {
    private final Long id;
    private final String email;

    private LoginResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static LoginResponse from(User user) {
        return new LoginResponse(user.getId(), user.getEmail());
    }
}
