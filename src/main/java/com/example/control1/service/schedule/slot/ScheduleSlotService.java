package com.example.control1.service.schedule.slot;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.slot.ScheduleSlotCreateDTO;
import com.example.control1.dto.schedule.slot.ScheduleSlotDTO;
import org.springframework.data.domain.Page;

public interface ScheduleSlotService {
    CreateResponseDTO createScheduleSlot(ScheduleSlotCreateDTO scheduleSlotCreateDTO);
    ScheduleSlotDTO getScheduleSlot(String id);
    Page<ScheduleSlotDTO> getAllScheduleSlots(Integer page, Integer size);
}
