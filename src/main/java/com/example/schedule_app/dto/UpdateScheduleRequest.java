package com.example.schedule_app.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private String title;
    private String contents;
    private Long userId;
}
