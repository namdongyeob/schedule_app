package com.example.schedule_app.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteScheduleRequest {
    @NotBlank
    private Long userId;
}
