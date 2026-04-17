package com.example.schedule_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteScheduleRequest {
    @NotBlank
    private Long userId;
}
