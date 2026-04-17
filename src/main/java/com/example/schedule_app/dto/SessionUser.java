package com.example.schedule_app.dto;

import com.example.schedule_app.entity.User;
import lombok.Getter;

@Getter
public class SessionUser {
    private final Long id;
    private final String email;

    public SessionUser(Long id, String email) {
        this.id = id;
        this.email = email;
    }
    public static SessionUser from(User user) {
        return new SessionUser(user.getId(), user.getEmail());
    }
}