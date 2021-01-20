package com.kashyapbari.tatsam.priorityassignment.web.model;

import org.mapstruct.Mapper;

import com.kashyapbari.tatsam.priorityassignment.domain.Area;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AreaMapper {

    AreaMapper INSTANCE = Mappers.getMapper(AreaMapper.class);

    Area toArea(AreaDto areaDto);
    AreaDto toAreaDto(Area area);
    List<AreaDto> toAreaDtos(List<Area> areas);
}
