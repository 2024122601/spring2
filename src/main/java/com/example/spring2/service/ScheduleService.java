package com.example.spring2.service;

import com.example.spring2.dto.ScheduleRequestDto;
import com.example.spring2.dto.ScheduleResponseDto;
import com.example.spring2.entity.Schedule;
import com.example.spring2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto save(ScheduleRequestDto dto) {
        Schedule schedule = new Schedule(dto.getTitle(), dto.getTask());
        Schedule savedschedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedschedule.getId(), savedschedule.getTitle(), savedschedule.getTask());
    }
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        List<ScheduleResponseDto> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleResponseDto dto = new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getTask());
            dtos.add(dto);
        }

        return dtos;
    }
    @Transactional(readOnly = true)
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그 id 일정 없음")
        );

        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getTask());
    }

    @Transactional
    public ScheduleResponseDto update(Long id, ScheduleRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그 id 일정 없음")
        );

        schedule.update(dto.getTitle(), dto.getTask()); // 영속성 컨텍스트..?
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getTask());
    }
    @Transactional
    public void deleteById(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new IllegalArgumentException("그 id가진 일정 없어서 삭제 못함");
        }

        scheduleRepository.deleteById(id);
    }
}
