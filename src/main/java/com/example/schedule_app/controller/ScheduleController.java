package com.example.schedule_app.controller;

import com.example.schedule_app.dto.*;
import com.example.schedule_app.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<CreateScheduleResponse> create(
            @Valid @RequestBody CreateScheduleRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }
    @GetMapping
    public ResponseEntity<List<GetScheduleResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }
    @GetMapping("/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getOne(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> update(
            @PathVariable Long scheduleId,
            @Valid  @RequestBody UpdateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request));
    }
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long scheduleId,
            @Valid @RequestBody DeleteScheduleRequest request){
        scheduleService.delete(scheduleId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/page")
    public ResponseEntity<Page<SchedulePageResponse>> getPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAllWithPage(page, size));
    }
}
