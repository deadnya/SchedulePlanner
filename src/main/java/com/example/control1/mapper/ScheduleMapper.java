package com.example.control1.mapper;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.main.ScheduleCreateDTO;
import com.example.control1.dto.schedule.main.ScheduleDTO;
import com.example.control1.dto.schedule.period.SchedulePeriodCreateDTO;
import com.example.control1.dto.schedule.period.SchedulePeriodDTO;
import com.example.control1.dto.schedule.slot.ScheduleSlotCreateDTO;
import com.example.control1.dto.schedule.slot.ScheduleSlotDTO;
import com.example.control1.dto.schedule.template.ScheduleTemplateCreateDTO;
import com.example.control1.dto.schedule.template.ScheduleTemplateDTO;
import com.example.control1.entity.Schedule;
import com.example.control1.entity.SchedulePeriod;
import com.example.control1.entity.ScheduleSlot;
import com.example.control1.entity.ScheduleTemplate;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    Schedule toSchedule(ScheduleCreateDTO scheduleCreateDTO);
    CreateResponseDTO toResponseCreateDTO(Schedule schedule);
    ScheduleDTO toDTO(Schedule schedule);

    ScheduleTemplate toScheduleTemplate(ScheduleTemplateCreateDTO scheduleTemplateCreateDTO);
    CreateResponseDTO toCreateResponseDTO(ScheduleTemplate scheduleTemplate);
    ScheduleTemplateDTO toDTO(ScheduleTemplate scheduleTemplate);

    ScheduleSlot toScheduleSlot(ScheduleSlotCreateDTO scheduleSlotCreateDTO);
    CreateResponseDTO toCreateResponseDTO(ScheduleSlot scheduleSlot);
    ScheduleSlotDTO toDTO(ScheduleSlot scheduleSlot);

    SchedulePeriod toSchedulePeriod(SchedulePeriodCreateDTO schedulePeriodCreateDTO);
    CreateResponseDTO toCreateResponseDTO(SchedulePeriod schedulePeriod);
    SchedulePeriodDTO toDTO(SchedulePeriod schedulePeriod);
}
