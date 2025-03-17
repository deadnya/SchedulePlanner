package com.example.control1.controller.schedule;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.template.ScheduleTemplateCreateDTO;
import com.example.control1.dto.schedule.template.ScheduleTemplateDTO;
import com.example.control1.service.schedule.template.ScheduleTemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule/template")
public class ScheduleTemplateController {

    private final ScheduleTemplateService scheduleTemplateService;

    @PostMapping("/create")
    public ResponseEntity<CreateResponseDTO> createScheduleTemplate(
            @Valid @RequestBody ScheduleTemplateCreateDTO scheduleTemplateCreateDTO
    ) {
        return ResponseEntity.ok(scheduleTemplateService.createScheduleTemplate(scheduleTemplateCreateDTO));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ScheduleTemplateDTO> getScheduleTemplate(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(scheduleTemplateService.getScheduleTemplate(id));
    }

    @GetMapping("/get")
    public ResponseEntity<Page<ScheduleTemplateDTO>> getAllScheduleTemplates(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        return ResponseEntity.ok(scheduleTemplateService.getAllScheduleTemplates(page, size));
    }
}
