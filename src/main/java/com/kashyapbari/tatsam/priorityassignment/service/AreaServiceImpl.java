package com.kashyapbari.tatsam.priorityassignment.service;

import com.kashyapbari.tatsam.priorityassignment.domain.Area;
import com.kashyapbari.tatsam.priorityassignment.repository.AreaRepository;
import com.kashyapbari.tatsam.priorityassignment.web.model.AreaDto;
import com.kashyapbari.tatsam.priorityassignment.web.model.AreaMapper;
import com.kashyapbari.tatsam.priorityassignment.web.model.PageResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaRepository areaRepository;

    @Override
    public PageResponse<AreaDto> getAllArea(Pageable pageable) {
        Page<Area> areas = areaRepository.findAll(pageable);
        return new PageResponse(areas.getNumber(), areas.getSize(),
                areas.getTotalPages(), areas.getTotalElements(),
                AreaMapper.INSTANCE.toAreaDtos(areas.getContent()));
    }
}
