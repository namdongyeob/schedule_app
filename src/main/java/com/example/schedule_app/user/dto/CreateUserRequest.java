package com.example.schedule_app.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateUserRequest {
    @NotBlank
    private String username;
    @Email
    private String email;
    @Size(min = 8, max = 30)
    private String password;
}
