package com.kashyapbari.tatsam.priorityassignment.service;

import com.kashyapbari.tatsam.priorityassignment.web.model.PageResponse;
import com.kashyapbari.tatsam.priorityassignment.web.model.UserPriorityDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserPriorityService {
    public PageResponse<UserPriorityDto> getAllUserPriorities(Pageable paging);

    UserPriorityDto saveUserPriority(UserPriorityDto userPriorityDto);
}
