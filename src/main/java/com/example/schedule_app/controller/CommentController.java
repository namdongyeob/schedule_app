package com.example.schedule_app.controller;

import com.example.schedule_app.dto.CreateCommentRequest;
import com.example.schedule_app.dto.CreateCommentResponse;
import com.example.schedule_app.dto.GetCommentResponse;
import com.example.schedule_app.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CreateCommentResponse> createComment(
            @RequestBody CreateCommentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(request));
    }
    @GetMapping
    public ResponseEntity<List<GetCommentResponse>> getCommentAll(){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findAll());
    }
}
