package com.example.control1.service.schedule.template;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.template.ScheduleTemplateCreateDTO;
import com.example.control1.dto.schedule.template.ScheduleTemplateDTO;
import org.springframework.data.domain.Page;

/**
 * Service interface for managing schedule templates.
 */
public interface ScheduleTemplateService {

    /**
     * Creates a new schedule template.
     *
     * @param scheduleTemplateCreateDTO data transfer object containing schedule template details
     * @return response containing the ID of the created schedule template
     */
    CreateResponseDTO createScheduleTemplate(ScheduleTemplateCreateDTO scheduleTemplateCreateDTO);

    /**
     * Retrieves a schedule template by its ID.
     *
     * @param id ID of the schedule template
     * @return schedule template data transfer object
     */
    ScheduleTemplateDTO getScheduleTemplate(String id);

    /**
     * Retrieves all schedule templates with pagination.
     *
     * @param page page number to retrieve
     * @param size number of schedule templates per page
     * @return a paginated list of schedule template data transfer objects
     */
    Page<ScheduleTemplateDTO> getAllScheduleTemplates(Integer page, Integer size);
}