package com.kashyapbari.tatsam.priorityassignment.service;

import com.kashyapbari.tatsam.priorityassignment.domain.Area;
import com.kashyapbari.tatsam.priorityassignment.domain.Priority;
import com.kashyapbari.tatsam.priorityassignment.domain.User;
import com.kashyapbari.tatsam.priorityassignment.exception.InvalidIdentifierException;
import com.kashyapbari.tatsam.priorityassignment.exception.PriorityInUseException;
import com.kashyapbari.tatsam.priorityassignment.repository.AreaRepository;
import com.kashyapbari.tatsam.priorityassignment.repository.PriorityRepository;
import com.kashyapbari.tatsam.priorityassignment.repository.UserRepository;
import com.kashyapbari.tatsam.priorityassignment.web.model.PageResponse;
import com.kashyapbari.tatsam.priorityassignment.web.model.PriorityDto;
import com.kashyapbari.tatsam.priorityassignment.web.model.UserPriorityDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserPriorityServiceImpl implements UserPriorityService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PriorityRepository priorityRepository;
    @Autowired
    private AreaRepository areaRepository;

    @Override
    public PageResponse<UserPriorityDto> getAllUserPriorities(Pageable paging) {
        log.debug("Getting User Priorities page{} count{}", paging.getPageNumber(),
                paging.getPageSize());
        List<UserPriorityDto> userPriorityDtos = new ArrayList<>();
        Page<User> userPage = userRepository.findAll(paging);
        List<User> users = userPage.getContent();
        log.debug("Users Length: {}",users.size());
        for(User user: users){
            UserPriorityDto userPriorityDto = new UserPriorityDto();
            userPriorityDto.setUserId(user.getId());
            userPriorityDto.setUserName(user.getUserName());
            userPriorityDto.setPriorityList(new ArrayList<>());
            List<Priority> priorities = user.getPriorityList();
            for(Priority priority: priorities){
                PriorityDto priorityDto = new PriorityDto();
                priorityDto.setId(priority.getId());
                priorityDto.setAreaId(priority.getArea().getId());
                priorityDto.setPriority_level(priority.getPriority_level());
                priorityDto.setSatisfaction(priority.getSatisfaction());
                userPriorityDto.getPriorityList().add(priorityDto);
            }
            userPriorityDtos.add(userPriorityDto);
        }
        return new PageResponse<>(userPage.getNumber(), userPage.getSize(),
                userPage.getTotalPages(), userPage.getTotalElements(),
                userPriorityDtos);
    }

    @Override
    public UserPriorityDto saveUserPriority(UserPriorityDto userPriorityDto) {
        User user = userRepository.findById(userPriorityDto.getUserId()).orElse(null);
//        validate user id provided
        if(user == null){
            throw new InvalidIdentifierException("Invalid Identifier for userId: "+userPriorityDto.getUserId());
        }
        log.debug("User found: {}",user.getUserName());
        for(PriorityDto priorityDto: userPriorityDto.getPriorityList()){
            Area area = areaRepository.findById(priorityDto.getAreaId()).orElse(null);
//            validate area id provided
            if(area == null){
                throw new InvalidIdentifierException("Invalid Identifier for areaId: "+priorityDto.getAreaId());
            }
            log.debug("Area found {}",area.getName());
            Priority priority = Priority.builder()
                    .user(user)
                    .area(area)
                    .build();
            Example<Priority> example = Example.of(priority);
//            check if entry exist of area for user
            priority = priorityRepository.findOne(example).orElse(priority);
            if (priorityDto.getPriority_level() != null)
            {
//            check if user is already using a priority level
                if (priorityRepository.findOne(Example.of(Priority.builder()
                        .priority_level(priorityDto.getPriority_level())
                        .user(user)
                        .build())).isPresent()){
                    log.debug("Priority already in use");
                    throw new PriorityInUseException("Priority level cannot be repeated :"+priorityDto.getPriority_level());
                }
                priority.setPriority_level(priorityDto.getPriority_level());
            }
            priority.setSatisfaction(priorityDto.getSatisfaction());
            priorityRepository.save(priority);
            priorityDto.setId(priority.getId());
        }
        return userPriorityDto;
    }
}
