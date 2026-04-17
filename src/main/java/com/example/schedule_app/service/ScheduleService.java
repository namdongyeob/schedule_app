package com.example.schedule_app.service;

import com.example.schedule_app.dto.*;
import com.example.schedule_app.entity.Schedule;
import com.example.schedule_app.entity.User;
import com.example.schedule_app.repository.ScheduleRepository;
import com.example.schedule_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContents(),
                user
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return CreateScheduleResponse.from(savedSchedule);
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(GetScheduleResponse::from)
                .toList();

    }

    @Transactional(readOnly = true)
    public GetScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다."));
        return GetScheduleResponse.from(schedule);
    }

    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다."));
        if (!schedule.getUser().getId().equals(request.getUserId())) {
            throw new IllegalStateException("작성자만 수정할 수 있습니다.");
        }
        schedule.update(request);
        return UpdateScheduleResponse.from(schedule);
    }

    @Transactional
    public void delete(Long scheduleId, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다."));
        if (!schedule.getUser().getId().equals(request.getUserId())) {
            throw new IllegalStateException("작성자만 삭제할 수 있습니다.");
        }
        scheduleRepository.delete(schedule);
    }
}
