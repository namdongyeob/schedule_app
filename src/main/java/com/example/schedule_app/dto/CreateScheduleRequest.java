package com.example.schedule_app.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String contents;
    private Long userId;
}
