package com.example.schedule_app.schedule.dto;

import com.example.schedule_app.schedule.entity.Schedule;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulePageResponse {
    private final String title;
    private final String contents;
    private final Long commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String username;

    private SchedulePageResponse(String title, String contents, Long commentCount, LocalDateTime createdAt, LocalDateTime modifiedAt, String username) {
        this.title = title;
        this.contents = contents;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.username = username;
    }
    public static SchedulePageResponse of(Schedule schedule, Long commentCount){
        return new SchedulePageResponse(
                schedule.getTitle(),
                schedule.getContents(),
                commentCount,
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                schedule.getUser().getUsername()
        );
    }
}
