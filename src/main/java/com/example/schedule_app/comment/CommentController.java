package com.example.schedule_app.comment;

import com.example.schedule_app.comment.dto.CreateCommentRequest;
import com.example.schedule_app.comment.dto.CreateCommentResponse;
import com.example.schedule_app.comment.dto.GetCommentResponse;
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
