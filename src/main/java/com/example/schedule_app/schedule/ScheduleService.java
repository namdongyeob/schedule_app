package com.example.schedule_app.schedule;

import com.example.schedule_app.user.entity.User;
import com.example.schedule_app.global.exception.InvalidPasswordException;
import com.example.schedule_app.global.exception.ScheduleNotFoundException;
import com.example.schedule_app.global.exception.UserNotFoundException;
import com.example.schedule_app.user.UserRepository;
import com.example.schedule_app.schedule.dto.*;
import com.example.schedule_app.schedule.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    // 일정 생성 - userId로 유저를 찾아 일정을 생성하고 저장
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        // userId로 유저 조회, 없으면 예외 발생
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 유저입니다.")
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
    // 일정 전체 조회 - stream으로 DTO 변환 후 반환
    public List<GetScheduleResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(GetScheduleResponse::from)
                .toList();

    }

    @Transactional(readOnly = true)
    // 일정 단건 조회 - scheduleId로 일정 조회, 없으면 예외 발생
    public GetScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다."));
        return GetScheduleResponse.from(schedule);
    }

    @Transactional
    // 일정 수정 - 작성자 본인만 수정 가능
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다."));
        if (!schedule.getUser().getId().equals(request.getUserId())) {
            throw new InvalidPasswordException("작성자만 수정할 수 있습니다.");
        }
        schedule.update(request);
        return UpdateScheduleResponse.from(schedule);
    }

    @Transactional
    // 일정 삭제 - 작성자 본인만 삭제 가능
    public void delete(Long scheduleId, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다."));
        if (!schedule.getUser().getId().equals(request.getUserId())) {
            throw new InvalidPasswordException("작성자만 삭제할 수 있습니다.");
        }
        scheduleRepository.delete(schedule);
    }

    @Transactional(readOnly = true)
    // 일정 페이징 조회 - 수정일 기준 내림차순, 댓글 개수 포함
    public Page<SchedulePageResponse> findAllWithPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return scheduleRepository.findAllWithCommentCount(pageable);
    }
}
