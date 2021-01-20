package com.kashyapbari.tatsam.priorityassignment.web.controller;

import com.kashyapbari.tatsam.priorityassignment.domain.Area;
import com.kashyapbari.tatsam.priorityassignment.service.AreaService;
import com.kashyapbari.tatsam.priorityassignment.web.model.AreaDto;
import com.kashyapbari.tatsam.priorityassignment.web.model.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/area")
@RestController
public class AreaController {
    @Autowired
    private AreaService areaService;

    @GetMapping()
    public ResponseEntity<PageResponse<AreaDto>> getAreas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ){
        Pageable paging = PageRequest.of(page, size);
        return new ResponseEntity(areaService.getAllArea(paging), HttpStatus.OK);
    }
}
