package com.example.schedule_app.service;

import com.example.schedule_app.dto.CreateCommentRequest;
import com.example.schedule_app.dto.CreateCommentResponse;
import com.example.schedule_app.dto.GetCommentResponse;
import com.example.schedule_app.entity.Comment;
import com.example.schedule_app.entity.Schedule;
import com.example.schedule_app.entity.User;
import com.example.schedule_app.exception.ScheduleNotFoundException;
import com.example.schedule_app.exception.UserNotFoundException;
import com.example.schedule_app.repository.CommentRepository;
import com.example.schedule_app.repository.ScheduleRepository;
import com.example.schedule_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateCommentResponse save(CreateCommentRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 유저입니다."));
        Schedule schedule = scheduleRepository.findById(request.getScheduleId()).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다."));
        Comment comment = new Comment(request.getContents(), user, schedule);
        Comment savedComment = commentRepository.save(comment);
        return CreateCommentResponse.from(savedComment);
    }

    @Transactional(readOnly = true)
    public List<GetCommentResponse> findAll() {
        return commentRepository.findAll()
                .stream().map(GetCommentResponse::from)
                .toList();
    }
}
