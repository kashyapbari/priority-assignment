package com.kashyapbari.tatsam.priorityassignment.service;

import com.kashyapbari.tatsam.priorityassignment.domain.User;
import com.kashyapbari.tatsam.priorityassignment.web.model.UserPriorityDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserPriorityService {
    public List<UserPriorityDto> getAllUserPriorities();

    UserPriorityDto saveUserPriority(UserPriorityDto userPriorityDto);
}
