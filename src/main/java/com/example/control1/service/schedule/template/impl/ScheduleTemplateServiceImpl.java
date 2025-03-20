package com.example.control1.service.schedule.template.impl;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.template.ScheduleTemplateCreateDTO;
import com.example.control1.dto.schedule.template.ScheduleTemplateDTO;
import com.example.control1.entity.ScheduleTemplate;
import com.example.control1.mapper.ScheduleMapper;
import com.example.control1.repository.ScheduleTemplateRepository;
import com.example.control1.service.schedule.template.ScheduleTemplateService;
import com.example.control1.service.uuid.UUIDService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implementation of the ScheduleTemplateService interface.
 * Provides functionality to manage schedule templates.
 */
@Service
@RequiredArgsConstructor
public class ScheduleTemplateServiceImpl implements ScheduleTemplateService {

    private final ScheduleTemplateRepository scheduleTemplateRepository;
    private final ScheduleMapper scheduleMapper;
    private final UUIDService uuidService;

    /**
     * Creates a new schedule template.
     *
     * @param scheduleTemplateCreateDTO data transfer object containing schedule template details
     * @return response containing the ID of the created schedule template
     */
    @Override
    public CreateResponseDTO createScheduleTemplate(ScheduleTemplateCreateDTO scheduleTemplateCreateDTO) {

        ScheduleTemplate scheduleTemplate = scheduleMapper.toScheduleTemplate(scheduleTemplateCreateDTO);
        scheduleTemplate.setId(uuidService.getRandomUUID());

        scheduleTemplateRepository.save(scheduleTemplate);

        return scheduleMapper.toCreateResponseDTO(scheduleTemplate);
    }

    /**
     * Retrieves a schedule template by its ID.
     *
     * @param id ID of the schedule template
     * @return schedule template data transfer object
     * @throws ResponseStatusException if the schedule template is not found
     */
    @Override
    public ScheduleTemplateDTO getScheduleTemplate(String id) {

        ScheduleTemplate scheduleTemplate = scheduleTemplateRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Schedule Template with id: %s not found", id))
        );

        return scheduleMapper.toDTO(scheduleTemplate);
    }

    /**
     * Retrieves all schedule templates with pagination.
     *
     * @param page page number to retrieve
     * @param size number of schedule templates per page
     * @return a paginated list of schedule template data transfer objects
     */
    @Override
    public Page<ScheduleTemplateDTO> getAllScheduleTemplates(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);

        return scheduleTemplateRepository.findAll(pageable).map(scheduleMapper::toDTO);
    }
}
