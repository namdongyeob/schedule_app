package com.example.schedule_app.entity;

import com.example.schedule_app.dto.UpdateScheduleRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor
public class Schedule extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;
    private String username;

    public Schedule(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
    public void update (UpdateScheduleRequest request){
        this.title = request.getTitle();
        this.contents = request.getContents();
    }
}

