package com.example.schedule_app.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteUserRequest {
    @NotBlank
    private String password;
}
