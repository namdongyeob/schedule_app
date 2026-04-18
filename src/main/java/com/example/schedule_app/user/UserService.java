package com.example.schedule_app.user;

import com.example.schedule_app.global.config.PasswordEncoder;
import com.example.schedule_app.user.entity.User;
import com.example.schedule_app.global.exception.InvalidPasswordException;
import com.example.schedule_app.global.exception.UserNotFoundException;
import com.example.schedule_app.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {
        User user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
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
                () -> new UserNotFoundException("없는 유저입니다."));
        return GetUserResponse.from(user);
    }

    @Transactional
    public UpdateUserResponse update(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다."));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }
        user.update(request);
        return UpdateUserResponse.from(user);
    }

    @Transactional
    public void delete(Long userId,DeleteUserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다."));
        if (!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }
        userRepository.delete(user);
    }
}
