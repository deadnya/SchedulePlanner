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

/**
 * REST controller for managing schedule templates.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule/template")
public class ScheduleTemplateController {

    private final ScheduleTemplateService scheduleTemplateService;

    /**
     * Creates a new schedule template.
     *
     * @param scheduleTemplateCreateDTO schedule template creation data transfer object
     * @return response entity containing the creation response data transfer object
     */
    @PostMapping("/create")
    public ResponseEntity<CreateResponseDTO> createScheduleTemplate(
            @Valid @RequestBody ScheduleTemplateCreateDTO scheduleTemplateCreateDTO
    ) {
        return ResponseEntity.ok(scheduleTemplateService.createScheduleTemplate(scheduleTemplateCreateDTO));
    }

    /**
     * Retrieves a schedule template by ID.
     *
     * @param id ID of the schedule template
     * @return response entity containing the schedule template data transfer object
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<ScheduleTemplateDTO> getScheduleTemplate(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(scheduleTemplateService.getScheduleTemplate(id));
    }

    /**
     * Retrieves all schedule templates with pagination.
     *
     * @param page page number to retrieve
     * @param size number of schedule templates per page
     * @return Response entity containing a page of schedule template data transfer objects
     */
    @GetMapping("/get")
    public ResponseEntity<Page<ScheduleTemplateDTO>> getAllScheduleTemplates(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        return ResponseEntity.ok(scheduleTemplateService.getAllScheduleTemplates(page, size));
    }
}
