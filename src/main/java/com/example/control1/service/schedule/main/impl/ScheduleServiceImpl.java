package com.example.control1.service.schedule.main.impl;

import com.example.control1.dto.common.CreateResponseDTO;
import com.example.control1.dto.schedule.main.ScheduleCreateDTO;
import com.example.control1.dto.schedule.main.ScheduleDTO;
import com.example.control1.entity.Schedule;
import com.example.control1.mapper.ScheduleMapper;
import com.example.control1.repository.ScheduleRepository;
import com.example.control1.service.schedule.main.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.example.control1.common.util.Utility.generateRandomUUID;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    public CreateResponseDTO createSchedule(ScheduleCreateDTO scheduleCreateDTO) {

        Schedule schedule = scheduleMapper.toSchedule(scheduleCreateDTO);
        schedule.setId(generateRandomUUID());

        scheduleRepository.save(schedule);

        return scheduleMapper.toResponseCreateDTO(schedule);
    }

    @Override
    public ScheduleDTO getSchedule(String request) {

        Optional<Schedule> schedule = scheduleRepository.findById(request);

        if (schedule.isEmpty()) {
            schedule = scheduleRepository.findByScheduleName(request);
        }

        if (schedule.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("could not find schedule by request: %s", request)
            );
        }

        return scheduleMapper.toDTO(schedule.get());
    }

    @Override
    public Page<ScheduleDTO> getAllSchedules(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);

        return scheduleRepository.findAll(pageable).map(scheduleMapper::toDTO);
    }
}
