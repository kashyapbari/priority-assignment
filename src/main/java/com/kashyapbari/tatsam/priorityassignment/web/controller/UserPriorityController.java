package com.kashyapbari.tatsam.priorityassignment.web.controller;

import com.kashyapbari.tatsam.priorityassignment.domain.User;
import com.kashyapbari.tatsam.priorityassignment.service.UserPriorityService;
import com.kashyapbari.tatsam.priorityassignment.web.model.UserPriorityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/userpriority")
@RestController
public class UserPriorityController {
    @Autowired
    private UserPriorityService userPriorityService;

//    public UserPriorityController(UserPriorityService userPriorityService) {
//        this.userPriorityService = userPriorityService;
//    }

    @GetMapping("/list")
    public ResponseEntity<List<UserPriorityDto>> getAllUserPriorities(){
        return new ResponseEntity<>(userPriorityService.getAllUserPriorities(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity savePriority(@RequestBody UserPriorityDto userPriorityDto){
        UserPriorityDto saveUserPriorityDto = userPriorityService.saveUserPriority(userPriorityDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/userpriority/"+saveUserPriorityDto.getUserId());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
}
