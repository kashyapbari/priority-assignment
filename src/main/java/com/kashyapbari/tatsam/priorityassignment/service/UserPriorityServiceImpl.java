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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<UserPriorityDto> userPriorityDtos = new ArrayList<>();
        Page<User> userPage = userRepository.findAll(paging);
        List<User> users = userPage.getContent();
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
        if(user == null){
            throw new InvalidIdentifierException("Invalid Identifier for userId: "+userPriorityDto.getUserId());
        }
        for(PriorityDto priorityDto: userPriorityDto.getPriorityList()){
            Area area = areaRepository.findById(priorityDto.getAreaId()).orElse(null);
            if(area == null){
                throw new InvalidIdentifierException("Invalid Identifier for areaId: "+priorityDto.getAreaId());
            }
            Priority priority = Priority.builder()
                    .user(user)
                    .area(area)
                    .build();
            Example<Priority> example = Example.of(priority);
            priority = priorityRepository.findOne(example).orElse(priority);
            if (priorityDto.getPriority_level() != null){
                if (priorityRepository.findOne(Example.of(Priority.builder()
                        .priority_level(priorityDto.getPriority_level())
                        .user(user)
                        .build())).isPresent()){
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
