package com.example.schedule_app.comment;

import com.example.schedule_app.comment.dto.CreateCommentRequest;
import com.example.schedule_app.comment.dto.CreateCommentResponse;
import com.example.schedule_app.comment.dto.GetCommentResponse;
import com.example.schedule_app.comment.entity.Comment;
import com.example.schedule_app.schedule.entity.Schedule;
import com.example.schedule_app.user.entity.User;
import com.example.schedule_app.global.exception.ScheduleNotFoundException;
import com.example.schedule_app.global.exception.UserNotFoundException;
import com.example.schedule_app.schedule.ScheduleRepository;
import com.example.schedule_app.user.UserRepository;
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
