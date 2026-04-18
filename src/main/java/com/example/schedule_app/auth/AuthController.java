package com.example.schedule_app.auth;

import com.example.schedule_app.auth.dto.LoginRequest;
import com.example.schedule_app.auth.dto.LoginResponse;
import com.example.schedule_app.auth.dto.SessionUser;
import com.example.schedule_app.user.entity.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auths")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request, HttpSession session){
        User user = authService.login(request);
        SessionUser sessionUser = SessionUser.from(user);
        session.setAttribute("loginUser", sessionUser);
        return ResponseEntity.status(HttpStatus.OK).body(LoginResponse.from(user));
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            HttpSession session) {
        if (sessionUser == null) {
            return ResponseEntity.badRequest().build();
        }
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
