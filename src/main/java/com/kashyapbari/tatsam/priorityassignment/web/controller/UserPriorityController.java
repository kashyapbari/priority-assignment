package com.kashyapbari.tatsam.priorityassignment.web.controller;

import com.kashyapbari.tatsam.priorityassignment.domain.User;
import com.kashyapbari.tatsam.priorityassignment.service.UserPriorityService;
import com.kashyapbari.tatsam.priorityassignment.web.model.PageResponse;
import com.kashyapbari.tatsam.priorityassignment.web.model.UserPriorityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/userpriority")
@RestController
public class UserPriorityController {
    @Autowired
    private UserPriorityService userPriorityService;

    @GetMapping()
    public ResponseEntity<PageResponse<UserPriorityDto>> getAllUserPriorities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page, size);
        return new ResponseEntity<>(userPriorityService.getAllUserPriorities(paging), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity savePriority(@Valid @RequestBody UserPriorityDto userPriorityDto){
        UserPriorityDto saveUserPriorityDto = userPriorityService.saveUserPriority(userPriorityDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/userpriority/"+saveUserPriorityDto.getUserId());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
}
