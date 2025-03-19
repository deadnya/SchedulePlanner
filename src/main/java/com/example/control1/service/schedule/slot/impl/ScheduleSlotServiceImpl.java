package com.example.control1.service.schedule.slot.impl;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.slot.ScheduleSlotCreateDTO;
import com.example.control1.dto.schedule.slot.ScheduleSlotDTO;
import com.example.control1.entity.ScheduleSlot;
import com.example.control1.entity.ScheduleTemplate;
import com.example.control1.mapper.ScheduleMapper;
import com.example.control1.repository.ScheduleSlotRepository;
import com.example.control1.repository.ScheduleTemplateRepository;
import com.example.control1.service.schedule.slot.ScheduleSlotService;
import com.example.control1.service.uuid.UUIDService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ScheduleSlotServiceImpl implements ScheduleSlotService {

    private final ScheduleSlotRepository scheduleSlotRepository;
    private final ScheduleTemplateRepository scheduleTemplateRepository;
    private final ScheduleMapper scheduleMapper;
    private final UUIDService uuidService;

    @Override
    public CreateResponseDTO createScheduleSlot(ScheduleSlotCreateDTO scheduleSlotCreateDTO) {

        ScheduleSlot scheduleSlot = scheduleMapper.toScheduleSlot(scheduleSlotCreateDTO);
        scheduleSlot.setId(uuidService.getRandomUUID());

        ScheduleTemplate scheduleTemplate = scheduleTemplateRepository.findById(
                scheduleSlotCreateDTO.scheduleTemplateId()
        ).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Schedule Template with id: %s not found", scheduleSlotCreateDTO.scheduleTemplateId())
                ));

        scheduleSlot.setScheduleTemplate(scheduleTemplate);

        scheduleSlotRepository.save(scheduleSlot);

        return scheduleMapper.toCreateResponseDTO(scheduleSlot);
    }

    @Override
    public ScheduleSlotDTO getScheduleSlot(String id) {

        ScheduleSlot scheduleSlot = scheduleSlotRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Schedule Slot with id: %s not found", id))
        );

        return scheduleMapper.toDTO(scheduleSlot);
    }

    @Override
    public Page<ScheduleSlotDTO> getAllScheduleSlots(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);

        return scheduleSlotRepository.findAll(pageable).map(scheduleMapper::toDTO);
    }
}
