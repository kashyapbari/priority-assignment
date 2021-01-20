package com.kashyapbari.tatsam.priorityassignment.service;

import com.kashyapbari.tatsam.priorityassignment.domain.Area;
import com.kashyapbari.tatsam.priorityassignment.repository.AreaRepository;
import com.kashyapbari.tatsam.priorityassignment.web.model.AreaDto;
import com.kashyapbari.tatsam.priorityassignment.web.model.AreaMapper;
import com.kashyapbari.tatsam.priorityassignment.web.model.PageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaRepository areaRepository;

    @Override
    public PageResponse<AreaDto> getAllArea(Pageable paging) {
        log.debug("Getting User Priorities page{} count{}", paging.getPageNumber(),
                paging.getPageSize());
        Page<Area> areas = areaRepository.findAll(paging);
        return new PageResponse(areas.getNumber(), areas.getSize(),
                areas.getTotalPages(), areas.getTotalElements(),
                AreaMapper.INSTANCE.toAreaDtos(areas.getContent()));
    }
}
