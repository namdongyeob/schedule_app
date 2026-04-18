package com.example.schedule_app.schedule;

import com.example.schedule_app.schedule.dto.SchedulePageResponse;
import com.example.schedule_app.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    @Query("SELECT new com.example.schedule_app.schedule.dto.SchedulePageResponse(" +
            "s.title, s.contents, COUNT(c), s.createdAt, s.modifiedAt, s.user.username) " +
            "FROM Schedule s LEFT JOIN Comment c ON c.schedule = s " +
            "GROUP BY s ORDER BY s.modifiedAt DESC")
    Page<SchedulePageResponse> findAllWithCommentCount(Pageable pageable);
}
