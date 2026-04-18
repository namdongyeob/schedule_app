package com.example.schedule_app.auth;

import com.example.schedule_app.auth.dto.LoginRequest;
import com.example.schedule_app.global.config.PasswordEncoder;
import com.example.schedule_app.user.entity.User;
import com.example.schedule_app.global.exception.InvalidPasswordException;
import com.example.schedule_app.global.exception.UserNotFoundException;
import com.example.schedule_app.user.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 유저입니다."));
        if (!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }
}
