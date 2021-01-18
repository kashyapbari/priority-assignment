package com.kashyapbari.tatsam.priorityassignment.service;

import com.kashyapbari.tatsam.priorityassignment.domain.Area;
import com.kashyapbari.tatsam.priorityassignment.domain.Priority;
import com.kashyapbari.tatsam.priorityassignment.domain.User;
import com.kashyapbari.tatsam.priorityassignment.repository.AreaRepository;
import com.kashyapbari.tatsam.priorityassignment.repository.PriorityRepository;
import com.kashyapbari.tatsam.priorityassignment.repository.UserRepository;
import com.kashyapbari.tatsam.priorityassignment.web.model.PriorityDto;
import com.kashyapbari.tatsam.priorityassignment.web.model.UserPriorityDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<UserPriorityDto> getAllUserPriorities() {
        List<UserPriorityDto> userPriorityDtos = new ArrayList<>();
        List<User> users = userRepository.findAll();
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
        return userPriorityDtos;
    }

    @Override
    public UserPriorityDto saveUserPriority(UserPriorityDto userPriorityDto) {
        try {
            User user = userRepository.findById(userPriorityDto.getUserId()).orElseThrow();
            for(PriorityDto priorityDto: userPriorityDto.getPriorityList()){
                Area area = areaRepository.findById(priorityDto.getAreaId()).orElseThrow( );
                Priority priority = Priority.builder()
                        .user(user)
                        .priority_level(priorityDto.getPriority_level())
                        .satisfaction(priorityDto.getSatisfaction())
                        .area(area)
                        .build();
                priorityRepository.save(priority);
                priorityDto.setId(priority.getId());
            }
            return userPriorityDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
