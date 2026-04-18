package com.example.schedule_app.comment.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String contents;
    private Long userId;
    private Long scheduleId;
}
