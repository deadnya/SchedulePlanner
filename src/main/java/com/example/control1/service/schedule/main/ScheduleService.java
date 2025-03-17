package com.example.control1.service.schedule.main;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.main.ScheduleCreateDTO;
import com.example.control1.dto.schedule.main.ScheduleDTO;
import org.springframework.data.domain.Page;

public interface ScheduleService {
    CreateResponseDTO createSchedule(ScheduleCreateDTO scheduleCreateDTO);
    ScheduleDTO getSchedule(String request);
    Page<ScheduleDTO> getAllSchedules(Integer page, Integer size);
}
