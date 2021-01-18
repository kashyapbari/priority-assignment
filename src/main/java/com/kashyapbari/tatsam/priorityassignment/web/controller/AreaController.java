package com.kashyapbari.tatsam.priorityassignment.web.controller;

import com.kashyapbari.tatsam.priorityassignment.service.AreaService;
import com.kashyapbari.tatsam.priorityassignment.web.model.AreaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/area")
@RestController
public class AreaController {
    @Autowired
    private AreaService areaService;

    @GetMapping
    public ResponseEntity<List<AreaDto>> getAllAreas(){
        return new ResponseEntity<>(areaService.getAllArea(), HttpStatus.OK);
    }
}
