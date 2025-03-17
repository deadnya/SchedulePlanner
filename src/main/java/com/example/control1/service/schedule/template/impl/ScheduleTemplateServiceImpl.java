package com.example.control1.service.schedule.template.impl;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.template.ScheduleTemplateCreateDTO;
import com.example.control1.dto.schedule.template.ScheduleTemplateDTO;
import com.example.control1.entity.ScheduleTemplate;
import com.example.control1.mapper.ScheduleMapper;
import com.example.control1.repository.ScheduleTemplateRepository;
import com.example.control1.service.schedule.template.ScheduleTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.example.control1.common.util.Utility.generateRandomUUID;

@Service
@RequiredArgsConstructor
public class ScheduleTemplateServiceImpl implements ScheduleTemplateService {

    private final ScheduleTemplateRepository scheduleTemplateRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    public CreateResponseDTO createScheduleTemplate(ScheduleTemplateCreateDTO scheduleTemplateCreateDTO) {

        ScheduleTemplate scheduleTemplate = scheduleMapper.toScheduleTemplate(scheduleTemplateCreateDTO);
        scheduleTemplate.setId(generateRandomUUID());

        scheduleTemplateRepository.save(scheduleTemplate);

        return scheduleMapper.toCreateResponseDTO(scheduleTemplate);
    }

    @Override
    public ScheduleTemplateDTO getScheduleTemplate(String id) {

        ScheduleTemplate scheduleTemplate = scheduleTemplateRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Schedule Template with id: %s not found", id))
        );

        return scheduleMapper.toDTO(scheduleTemplate);
    }

    @Override
    public Page<ScheduleTemplateDTO> getAllScheduleTemplates(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);

        return scheduleTemplateRepository.findAll(pageable).map(scheduleMapper::toDTO);
    }
}
