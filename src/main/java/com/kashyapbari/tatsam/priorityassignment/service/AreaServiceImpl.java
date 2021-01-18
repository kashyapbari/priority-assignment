package com.kashyapbari.tatsam.priorityassignment.service;

import com.kashyapbari.tatsam.priorityassignment.domain.Area;
import com.kashyapbari.tatsam.priorityassignment.repository.AreaRepository;
import com.kashyapbari.tatsam.priorityassignment.web.model.AreaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaRepository areaRepository;

    @Override
    public List<AreaDto> getAllArea() {
        List<AreaDto> areaDtos = new ArrayList<>();
        List<Area> areas = areaRepository.findAll();
        areas.forEach(area -> {
            areaDtos.add(AreaDto.builder()
                    .id(area.getId())
                    .name(area.getName())
                    .build());
        });
        return areaDtos;
    }
}
