package com.example.schedule_app.controller;

import com.example.schedule_app.dto.*;
import com.example.schedule_app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserResponse> create(
            @Valid @RequestBody CreateUserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }
    @GetMapping
    public ResponseEntity<List<GetUserResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<GetUserResponse> getOne(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findOne(userId));
    }
    @PatchMapping("/{userId}")
    public ResponseEntity<UpdateUserResponse> update(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userId, request));
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long userId,
            @Valid @RequestBody DeleteUserRequest request){
       userService.delete(userId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
