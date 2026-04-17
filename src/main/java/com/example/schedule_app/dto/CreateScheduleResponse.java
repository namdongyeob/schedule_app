package com.example.schedule_app.dto;

import com.example.schedule_app.entity.Schedule;
import lombok.Getter;

@Getter
public class CreateScheduleResponse {
    private final Long id;
    private final String title;
    private final String contents;
    private final String username;

    private CreateScheduleResponse(Long id, String title, String contents, String username) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.username = username;
    }

    public static CreateScheduleResponse from(Schedule schedule) {
        return new CreateScheduleResponse(
        schedule.getId(),
        schedule.getTitle(),
        schedule.getContents(),
        schedule.getUsername()
        );
    }
}
