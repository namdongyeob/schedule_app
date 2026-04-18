package com.example.schedule_app.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCommentRequest {
    @NotBlank(message = "댓글 내용을 입력해주세요.")
    private String contents;
    private Long userId;
    private Long scheduleId;
}
