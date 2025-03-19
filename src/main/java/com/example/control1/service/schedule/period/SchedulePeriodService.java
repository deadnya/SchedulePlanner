package com.example.control1.service.schedule.period;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.period.OutputSettings;
import com.example.control1.dto.schedule.period.SchedulePeriodCreateDTO;
import com.example.control1.dto.schedule.period.SchedulePeriodDTO;
import org.springframework.data.domain.Page;

public interface SchedulePeriodService {
    CreateResponseDTO createSchedulePeriod(SchedulePeriodCreateDTO schedulePeriodCreateDTO, String currentUserId);
    SchedulePeriodDTO getSchedulePeriod(String id);
    Page<SchedulePeriodDTO> getAllSchedulePeriods(OutputSettings outputSettings);
}
