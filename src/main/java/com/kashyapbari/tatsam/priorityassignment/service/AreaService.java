package com.kashyapbari.tatsam.priorityassignment.service;

import com.kashyapbari.tatsam.priorityassignment.web.model.AreaDto;
import com.kashyapbari.tatsam.priorityassignment.web.model.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AreaService {

    PageResponse<AreaDto> getAllArea(Pageable pageable);
}
