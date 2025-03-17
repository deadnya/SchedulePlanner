package com.example.control1.controller.schedule;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.main.ScheduleCreateDTO;
import com.example.control1.dto.schedule.main.ScheduleDTO;
import com.example.control1.service.schedule.main.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule/main")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/create")
    public ResponseEntity<CreateResponseDTO> createSchedule(
            @Valid @RequestBody ScheduleCreateDTO scheduleCreateDTO
    ) {
        return ResponseEntity.ok(scheduleService.createSchedule(scheduleCreateDTO));
    }

    @GetMapping("/get/{request}")
    public ResponseEntity<ScheduleDTO> getSchedule(
            @PathVariable String request
    ) {
        return ResponseEntity.ok(scheduleService.getSchedule(request));
    }

    @GetMapping("/get")
    public ResponseEntity<Page<ScheduleDTO>> getAllSchedules(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        return ResponseEntity.ok(scheduleService.getAllSchedules(page, size));
    }
}
