package com.example.schedule_app.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String contents;
    private Long userId;
    private Long scheduleId;
}
