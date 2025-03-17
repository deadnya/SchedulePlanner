package com.example.control1.service.schedule.template;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.template.ScheduleTemplateCreateDTO;
import com.example.control1.dto.schedule.template.ScheduleTemplateDTO;
import org.springframework.data.domain.Page;

public interface ScheduleTemplateService {
    CreateResponseDTO createScheduleTemplate(ScheduleTemplateCreateDTO scheduleTemplateCreateDTO);
    ScheduleTemplateDTO getScheduleTemplate(String id);
    Page<ScheduleTemplateDTO> getAllScheduleTemplates(Integer page, Integer size);
}