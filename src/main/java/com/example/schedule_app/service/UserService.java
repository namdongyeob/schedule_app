package com.example.schedule_app.service;

import com.example.schedule_app.dto.*;
import com.example.schedule_app.entity.Schedule;
import com.example.schedule_app.entity.User;
import com.example.schedule_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {
        User user = new User(
                request.getUsername(),
                request.getEmail()
        );
        User savedUser = userRepository.save(user);
        return CreateUserResponse.from(savedUser);
    }

    @Transactional(readOnly = true)
    public List<GetUserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(GetUserResponse::from)
                .toList();

    }

    @Transactional(readOnly = true)
    public GetUserResponse findOne(Long userId) {
            User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다."));
        return GetUserResponse.from(user);
    }

    @Transactional
    public UpdateUserResponse update(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다."));
        user.update(request);
        return UpdateUserResponse.from(user);
    }

    @Transactional
    public void delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다."));
        userRepository.delete(user);
    }
}
