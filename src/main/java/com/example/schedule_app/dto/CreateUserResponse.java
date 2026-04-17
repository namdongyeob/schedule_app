package com.example.schedule_app.dto;

import com.example.schedule_app.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateUserResponse {
private final Long id;
private final String username;
private final String email;
private final LocalDateTime createdAt;
private final LocalDateTime modifiedAt;

    private CreateUserResponse(Long id, String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
        public static CreateUserResponse from(User user){
        return new CreateUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }
}
