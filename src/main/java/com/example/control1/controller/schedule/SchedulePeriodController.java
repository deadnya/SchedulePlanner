package com.example.control1.controller.schedule;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.period.SchedulePeriodCreateDTO;
import com.example.control1.dto.schedule.period.SchedulePeriodDTO;
import com.example.control1.service.schedule.period.SchedulePeriodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule/period")
public class SchedulePeriodController {

    private final SchedulePeriodService schedulePeriodService;

    @PostMapping("/create")
    public ResponseEntity<CreateResponseDTO> createSchedulePeriod(
            @Valid @RequestBody SchedulePeriodCreateDTO schedulePeriodCreateDTO
    ) {
        return ResponseEntity.ok(schedulePeriodService.createSchedulePeriod(schedulePeriodCreateDTO));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SchedulePeriodDTO> getSchedulePeriod(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(schedulePeriodService.getSchedulePeriod(id));
    }

    @GetMapping("/get")
    public ResponseEntity<Page<SchedulePeriodDTO>> getAllSchedulePeriods(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        return ResponseEntity.ok(schedulePeriodService.getAllSchedulePeriods(page, size));
    }
}
