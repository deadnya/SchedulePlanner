package com.example.control1.controller.schedule;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.slot.ScheduleSlotCreateDTO;
import com.example.control1.dto.schedule.slot.ScheduleSlotDTO;
import com.example.control1.service.schedule.slot.ScheduleSlotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule/slot")
public class ScheduleSlotController {

    private final ScheduleSlotService scheduleSlotService;

    @PostMapping("/create")
    public ResponseEntity<CreateResponseDTO> createScheduleSlot(
            @Valid @RequestBody ScheduleSlotCreateDTO scheduleSlotCreateDTO
    ) {
        return ResponseEntity.ok(scheduleSlotService.createScheduleSlot(scheduleSlotCreateDTO));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ScheduleSlotDTO> getScheduleSlot(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(scheduleSlotService.getScheduleSlot(id));
    }

    @GetMapping("/get")
    public ResponseEntity<Page<ScheduleSlotDTO>> getAllScheduleSlots(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        return ResponseEntity.ok(scheduleSlotService.getAllScheduleSlots(page, size));
    }
}
